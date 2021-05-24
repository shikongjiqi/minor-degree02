package edu.huc.service;

import edu.huc.bean.EntryForm;
import edu.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IEntryFormService {
    RespData apply(EntryForm entryForm, String name, String name1, String username);

    RespData check(Integer id);

    RespData queryToAudit(int page);

    RespData checkApply(int entryFormId);

    RespData queryApplyUser(int page);

    RespData allApply();

    void deleteEntry(int entryFormId);
}
