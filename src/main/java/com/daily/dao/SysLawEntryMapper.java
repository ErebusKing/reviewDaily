package com.daily.dao;

import com.daily.domain.SysLawEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysLawEntryMapper{

    void addLawEntry(SysLawEntry lawEntry);

    List<SysLawEntry> list_Study_LawEntries();

    List<SysLawEntry> list_Study_All();

    List<SysLawEntry> list_LastHard_LawEntries();

    List<SysLawEntry> list_Hard();

}
