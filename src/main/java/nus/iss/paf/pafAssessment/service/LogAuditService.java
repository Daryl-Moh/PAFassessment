package nus.iss.paf.pafAssessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import nus.iss.paf.pafAssessment.models.Transfer;

@Service
public class LogAuditService {

    private static final String TRANSACTION_ENTITY = "transactionlog";
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void saveToRedis(final Transfer trf){
        redisTemplate.opsForList()
            .leftPush(TRANSACTION_ENTITY, trf.toJson(trf));

    }
}