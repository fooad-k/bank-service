package com.bankservice.bankservice.service.impl;

import com.bankservice.bankservice.entity.Failed;
import com.bankservice.bankservice.repository.FailedRepository;
import com.bankservice.bankservice.service.IFailedService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class FailedService implements IFailedService {
    private final FailedRepository failedRepository;

    public Boolean create(Failed failed) {
        failedRepository.save(failed);
        return true;
    }
}
