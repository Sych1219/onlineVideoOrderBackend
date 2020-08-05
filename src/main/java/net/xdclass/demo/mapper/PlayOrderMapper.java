package net.xdclass.demo.mapper;

import net.xdclass.demo.model.entity.PlayOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayOrderMapper {
    int saveRecord(PlayOrder playOrder);
}
