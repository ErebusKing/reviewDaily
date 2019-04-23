package com.daily.service.impl;

import com.daily.dao.SysLawEntryMapper;
import com.daily.domain.SysLawEntry;
import com.daily.service.SysLawEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="lawEntryService")
public class SysLawEntryServiceImpl implements SysLawEntryService {

    @Autowired
    SysLawEntryMapper sysLawEntryMapper;

    @Override
    public void addLawEntry(SysLawEntry lawEntry) {

        sysLawEntryMapper.addLawEntry(lawEntry);
    }

    @Override
    public List<SysLawEntry> list_Study_LawEntries() {
        return  sysLawEntryMapper.list_Study_LawEntries();
    }


}
