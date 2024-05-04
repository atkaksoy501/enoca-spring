package com.enoca.challenge.atakanaksoy.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
    ModelMapper forUpdate();
}
