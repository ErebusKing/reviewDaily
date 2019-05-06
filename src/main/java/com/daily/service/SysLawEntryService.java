package com.daily.service;

import com.daily.domain.SysLawEntry;

import java.util.List;

public interface SysLawEntryService {

    void addLawEntry(SysLawEntry lawEntry);

    List<SysLawEntry> list_Study_LawEntries();

    List<SysLawEntry> list_Study_All();

    List<SysLawEntry> list_LastHard_LawEntries();

    List<SysLawEntry> list_Hard();

}
