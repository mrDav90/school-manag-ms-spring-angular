package com.dav.labs.program_service.kinds.services.impl;

import com.dav.labs.program_service.kinds.dto.requests.KindDtoRequest;
import com.dav.labs.program_service.kinds.dto.responses.KindDtoResponse;
import com.dav.labs.program_service.kinds.entities.KindEntity;
import com.dav.labs.program_service.kinds.mapper.KindMapper;
import com.dav.labs.program_service.kinds.repository.KindRepository;
import com.dav.labs.program_service.kinds.services.IKindService;
import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KindServiceImpl implements IKindService {
    private final KindRepository kindRepository;
    private final KindMapper kindMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(KindServiceImpl.class);

    @Override
    public Optional<KindDtoResponse> saveKind(KindDtoRequest kindDtoRequest){
        if (kindRepository.findByName(kindDtoRequest.getName()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("kind.exists", new Object[]{kindDtoRequest.getName()}, Locale.getDefault()));
        }
        KindEntity kind = kindMapper.toKindEntity(kindDtoRequest);
        logger.info("Kind (Niveau): {}", kind);
        KindEntity kindEntity = kindRepository.save(kind);
        KindDtoResponse kindDtoResponse = kindMapper.toKindDtoResponse(kindEntity);
        return Optional.of(kindDtoResponse);
    }
    @Override
    public Optional<List<KindDtoResponse>> getAllKinds(){
        List<KindEntity> kindsEntities = kindRepository.findAll();
        return Optional.of(kindMapper.toKindDtoResponseList(kindsEntities));
    }
    @Override
    public Optional<KindDtoResponse> getKindById(Long id){
        return kindRepository.findById(id)
                .map(kind -> Optional.of(kindMapper.toKindDtoResponse(kind)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("kind.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<KindDtoResponse> updateKind(Long id, KindDtoRequest kindDtoRequest){
        return kindRepository.findById(id)
                .map(kind -> {
                    kind.setName(kindDtoRequest.getName());
                    kind.setDescription(kindDtoRequest.getDescription());
                    var kindEntity = kindRepository.save(kind);
                    return Optional.of(kindMapper.toKindDtoResponse(kindEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("kind.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteKind(Long id){
        if (kindRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("kind.notfound", new Object[]{id}, Locale.getDefault()));
        }
        kindRepository.deleteById(id);
        return true;
    }
}
