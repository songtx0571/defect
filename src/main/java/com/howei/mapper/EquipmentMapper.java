package com.howei.mapper;

import com.howei.pojo.Equipment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface EquipmentMapper {

    List<Equipment> getEquMap(Map map);

}
