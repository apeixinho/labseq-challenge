package org.labseq.api.controller;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(LabSeqController.BASE_URL)
public class LabSeqController {

    public static final String BASE_URL = "/api/v1/";
    private static Map<Integer, BigInteger> labSeqMap;
    static {
        labSeqMap = new ConcurrentHashMap<>();
        labSeqMap.put(0, BigInteger.ZERO);
        labSeqMap.put(1, BigInteger.ONE);
        labSeqMap.put(2, BigInteger.ZERO);
        labSeqMap.put(3, BigInteger.ONE);
    }

    @RequestMapping(value = "labseq/{n}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "getIndexCache")
    public String getIndex(@PathVariable @NotNull @Min(value = 0) @Max(value = 20000) Integer n) {
        return buildSequence(n);
    }

    private static String buildSequence(Integer n) {
        for (int key = 4; key <= n; key++) {
            int keyFinal = key;
            labSeqMap.computeIfAbsent(key, value -> labSeqMap.get(keyFinal - 4).add(labSeqMap.get(keyFinal - 3)));
        }
        return labSeqMap.get(n).toString();
    }

}