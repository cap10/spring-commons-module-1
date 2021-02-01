package zw.co.invenico.springcommonsmodule.aspects;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auditFeignClient", url = "http://localhost:8080")
public interface AuditFeignClientService {

    @PostMapping("/audits")
    Object create(@RequestBody AuditActionRequest auditActionRequest);
}
