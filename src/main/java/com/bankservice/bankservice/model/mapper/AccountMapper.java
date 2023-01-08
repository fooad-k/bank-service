package com.bankservice.bankservice.model.mapper;

import com.bankservice.bankservice.entity.Account;
import com.bankservice.bankservice.model.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountDTO toDTO(Account source);
    AccountDTO.Info toInfoDTO(Account source);
}
