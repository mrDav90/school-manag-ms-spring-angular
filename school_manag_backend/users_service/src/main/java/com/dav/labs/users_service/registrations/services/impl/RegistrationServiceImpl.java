package com.dav.labs.users_service.registrations.services.impl;

import com.dav.labs.users_service.clients.feign.interfaces.AcademicYearRestClient;
import com.dav.labs.users_service.clients.feign.interfaces.ClasseRestClient;
import com.dav.labs.users_service.exception.EntityExistsException;
import com.dav.labs.users_service.exception.EntityNotFoundException;
import com.dav.labs.users_service.registrations.dto.requests.RegistrationDtoRequest;
import com.dav.labs.users_service.registrations.dto.responses.RegistrationDtoResponse;
import com.dav.labs.users_service.registrations.entities.RegisterStatus;
import com.dav.labs.users_service.registrations.entities.RegistrationEntity;
import com.dav.labs.users_service.registrations.mapper.RegistrationMapper;
import com.dav.labs.users_service.registrations.repository.RegistrationRepository;
import com.dav.labs.users_service.registrations.services.IRegistrationService;
import com.dav.labs.users_service.students.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements IRegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final MessageSource messageSource;
    private final AcademicYearRestClient academicYearRestClient;
    private final ClasseRestClient classeRestClient;
    private final IStudentService studentService ;
    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final KafkaTemplate<String, RegistrationDtoResponse> kafkaTemplate;


    @Override
    public Optional<RegistrationDtoResponse> saveRegistration(RegistrationDtoRequest registrationDtoRequest){

        if (registrationRepository.findByStudentIdAndAcademicYearId(registrationDtoRequest.getStudentId(), registrationDtoRequest.getAcademicYearId()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("registration.exists", new Object[]{studentService.getStudentById(registrationDtoRequest.getStudentId()).get().getFirstName()+" "+studentService.getStudentById(registrationDtoRequest.getStudentId()).get().getLastName() , academicYearRestClient.getAcademicYearById(registrationDtoRequest.getAcademicYearId()).getLabel()}, Locale.getDefault()));
        }

        RegistrationEntity r = new RegistrationEntity();

        var registrationNu = "ETU-";
        var size = registrationRepository.count();
        if (size < 10){
            registrationNu += "00000"+size;
        }else if (size < 100){
            registrationNu += "0000"+size;
        }else if (size < 1000){
            registrationNu += "000"+size;
        } else if (size < 10000){
            registrationNu += "00"+size;
        }else if (size < 100000){
            registrationNu += "0"+size;
        }else {
            registrationNu += size;
        }

        r.setRegistrationNu(registrationNu);
        r.setRegistrationDate(LocalDateTime.now());
        r.setStudentId(registrationDtoRequest.getStudentId());
        r.setClasseId(registrationDtoRequest.getClasseId());
        r.setAcademicYearId(registrationDtoRequest.getAcademicYearId());
        r.setStatus(RegisterStatus.PENDING);

        logger.info("Registration: {}", r);

        RegistrationEntity registrationEntity = registrationRepository.save(r);
        RegistrationDtoResponse registrationDtoResponse = registrationMapper.toRegistrationDtoResponse(registrationEntity);
        kafkaTemplate.send("school-management-topic", registrationDtoResponse.getId(), registrationDtoResponse);
        System.out.println(kafkaTemplate.send("school-management-topic", registrationDtoResponse.getId(), registrationDtoResponse));
        return Optional.of(registrationDtoResponse);
    }
    @Override
    public Optional<List<RegistrationDtoResponse>> getAllRegistrations(){
        List<RegistrationEntity> registrationEntities = registrationRepository.findAll().stream().map(elt -> {

            elt.setAcademicYear(academicYearRestClient.getAcademicYearById(elt.getAcademicYearId()));
            elt.setClasse(classeRestClient.getClasseById(elt.getClasseId()));
            elt.setStudent(studentService.getStudentById(elt.getStudentId()).get());

            return elt;
        }).collect(Collectors.toList());

        return Optional.of(registrationMapper.toRegistrationDtoResponseList(registrationEntities));
    }
    @Override
    public Optional<RegistrationDtoResponse> getRegistrationById(String registrationId){
        return registrationRepository.findById(registrationId)
                .map(registration -> {
                    registration.setAcademicYear(academicYearRestClient.getAcademicYearById(registration.getAcademicYearId()));
                    registration.setClasse(classeRestClient.getClasseById(registration.getClasseId()));
                    registration.setStudent(studentService.getStudentById(registration.getStudentId()).get());

                    return Optional.of(registrationMapper.toRegistrationDtoResponse(registration)) ;
                })
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{registrationId}, Locale.getDefault())));
    }
    @Override
    public Optional<RegistrationDtoResponse> updateRegistration(String registrationId, RegistrationDtoRequest registrationDtoRequest){
        return registrationRepository.findById(registrationId)
                .map(registration -> {
                    registration.setStudentId(registrationDtoRequest.getStudentId());
                    registration.setClasseId(registrationDtoRequest.getClasseId());
                    registration.setAcademicYearId(registrationDtoRequest.getAcademicYearId());
                    var registrationEntity = registrationRepository.save(registration);
                    return Optional.of(registrationMapper.toRegistrationDtoResponse(registrationEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{registrationId}, Locale.getDefault())));
    }
    @Override
    public boolean deleteRegistration(String registrationId){
        if (registrationRepository.findById(registrationId).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{registrationId}, Locale.getDefault()));
        }
        registrationRepository.deleteById(registrationId);
        return true;
    }

}
