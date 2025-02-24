package com.dav.labs.program_service.kinds.services;



import com.dav.labs.program_service.kinds.dto.requests.KindDtoRequest;
import com.dav.labs.program_service.kinds.dto.responses.KindDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IKindService {
    Optional<KindDtoResponse> saveKind(KindDtoRequest kindDtoRequest);
    Optional<List<KindDtoResponse>> getAllKinds();
    Optional<KindDtoResponse> getKindById(Long kindId);
    Optional<KindDtoResponse> updateKind(Long kindId, KindDtoRequest kindDtoRequest);
    boolean deleteKind(Long kindId);
}
