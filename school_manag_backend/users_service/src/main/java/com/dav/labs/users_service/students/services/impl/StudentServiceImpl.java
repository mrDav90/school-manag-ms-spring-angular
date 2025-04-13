package com.dav.labs.users_service.students.services.impl;

import com.dav.labs.users_service.clients.keycloak.CreateKcClientResponse;
import com.dav.labs.users_service.clients.keycloak.IKcClientService;
import com.dav.labs.users_service.exception.EntityExistsException;
import com.dav.labs.users_service.exception.EntityNotFoundException;
import com.dav.labs.users_service.registrations.entities.RegistrationEntity;
import com.dav.labs.users_service.shared.utils.RegistrationNumber;
import com.dav.labs.users_service.students.dto.requests.StudentDtoRequest;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import com.dav.labs.users_service.students.entities.StudentEntity;
import com.dav.labs.users_service.students.mapper.StudentMapper;
import com.dav.labs.users_service.students.repository.StudentRepository;
import com.dav.labs.users_service.students.services.IStudentService;
import com.dav.labs.users_service.users.entities.Role;
import com.dav.labs.users_service.users.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements IStudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final IKcClientService kcClientService;
    private final RegistrationNumber registrationNumber = new RegistrationNumber();
    @Override
    public Optional<StudentDtoResponse> saveStudent(StudentDtoRequest studentDtoRequest){
        if (studentRepository.findByEmailPerso(studentDtoRequest.getEmailPerso()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("student.exists", new Object[]{studentDtoRequest.getEmailPerso()}, Locale.getDefault()));
        }
        StudentEntity student = studentMapper.toStudentEntity(studentDtoRequest);
        student.setEmailPro(studentDtoRequest.getEmailPerso().split("@")[0]+"@groupeisi.com");
        student.setRegistrationNu(registrationNumber.generate("ETD" , studentRepository.count()));
        student.setArchive(false);
        logger.info("Etudiant: {}", student);

        CreateKcClientResponse response = kcClientService.createUser(
                student.getFirstName(),
                student.getLastName(),
                student.getEmailPro(),
                student.getEmailPerso(),
                "passer",
                Role.STUDENT
        );
        System.out.println(response);
        if (response.getCreated()){
            StudentEntity studentEntity = studentRepository.save(student);
            StudentDtoResponse studentDtoResponse = studentMapper.toStudentDtoResponse(studentEntity);
            return Optional.of(studentDtoResponse);
        }
        return Optional.empty();
    }
    @Override
    public Optional<List<StudentDtoResponse>> getAllStudents(){
        List<StudentEntity> studentsEntities = studentRepository.findAll();
        return Optional.of(studentMapper.toStudentDtoResponseList(studentsEntities));
    }


    @Override
    @Cacheable(value = "students")
    public Page<StudentDtoResponse> getStudents(int pageNumber , int pageSize){
        Pageable pagedRequest = PageRequest.of(pageNumber,pageSize);
        Page<StudentEntity> studentsEntities = studentRepository.findAll(pagedRequest);
        return studentsEntities.map(studentMapper::toStudentDtoResponse);
    }

    @Override
    public Optional<StudentDtoResponse> getStudentById(String id){
        return studentRepository.findById(id)
                .map(student -> Optional.of(studentMapper.toStudentDtoResponse(student)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public Optional<StudentDtoResponse> updateStudent(String id, StudentDtoRequest studentDtoRequest){
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(studentDtoRequest.getFirstName());
                    student.setLastName(studentDtoRequest.getLastName());
                    student.setBirthDate(studentDtoRequest.getBirthDate());
                    student.setPlaceOfBirth(studentDtoRequest.getPlaceOfBirth());
                    student.setEmailPerso(studentDtoRequest.getEmailPerso());
                    student.setPhoneNumber(studentDtoRequest.getPhoneNumber());
                    student.setAddress(studentDtoRequest.getAddress());
                    student.setNationality(studentDtoRequest.getNationality());
                    student.setGender(studentDtoRequest.getGender());
                    student.setProfilePicture(studentDtoRequest.getProfilePicture());
                    student.setDocument(studentDtoRequest.getDocument());
                    student.setTutorFullName(studentDtoRequest.getTutorFullName());
                    student.setTutorPhoneNumber(studentDtoRequest.getTutorPhoneNumber());
                    var studentEntity = studentRepository.save(student);
                    return Optional.of(studentMapper.toStudentDtoResponse(studentEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public boolean deleteStudent(String id){
        var student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault()));
        }
        kcClientService.deleteUser(student.get().getEmailPro());
        studentRepository.deleteById(id);
        return true;
    }
}
