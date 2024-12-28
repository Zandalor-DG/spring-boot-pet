package org.springboot.pet.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.control.DeepClone;
import org.springboot.pet.dto.user.GetUserDto;
import org.springboot.pet.entity.User;

import java.util.Set;

@Mapper(mappingControl = DeepClone.class,
componentModel = MappingConstants.ComponentModel.SPRING,
builder = @Builder(disableBuilder = true))
public abstract class UserMapper {
    public abstract GetUserDto mapEntityToDto(User user);
}
