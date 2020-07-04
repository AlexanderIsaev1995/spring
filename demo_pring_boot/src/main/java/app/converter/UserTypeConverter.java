package app.converter;

import app.enums.UserType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        return attribute.ordinal();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        return UserType.values()[dbData];
    }
}