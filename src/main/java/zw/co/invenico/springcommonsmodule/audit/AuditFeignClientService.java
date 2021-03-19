package zw.co.invenico.springcommonsmodule.audit;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "akupay-api-gateway",url="https://api-akupay.jugaad.co.zw")
@RibbonClient("AKUPAY-AUDIT-TRAIL-SERVICE")
public interface AuditFeignClientService {

    @PostMapping("/akupay-audit-trail-service/api/v1/audits/create")
    Object create(@RequestBody AuditActionRequest auditActionRequest);
}
