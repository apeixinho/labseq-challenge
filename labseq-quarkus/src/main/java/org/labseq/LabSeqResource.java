package main.java.org.labseq;

import io.quarkus.cache.CacheResult;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@OpenAPIDefinition(
    info = @Info(
        title = "LabSeq API",
        version = "1.0.0",
        description = "API for calculating labseq sequence values"
    )
)
@Path("/api/v1/labseq")
@Produces(MediaType.TEXT_PLAIN)
public class LabSeqResource {
    private static Map<Integer, BigInteger> labSeqMap;
    static {
        labSeqMap = new ConcurrentHashMap<>();
        labSeqMap.put(0, BigInteger.ZERO);
        labSeqMap.put(1, BigInteger.ONE);
        labSeqMap.put(2, BigInteger.ZERO);
        labSeqMap.put(3, BigInteger.ONE);
    }

    @GET
    @Path("/{n}")
    @Operation(
        summary = "Get sequence value",
        description = "Returns the value at position n in the labseq sequence"
    )
    @APIResponse(
        responseCode = "200",
        description = "The sequence value at the specified position"
    )
    @CacheResult(cacheName = "labseq-cache")
    public String getSequence(
        @PathParam("n") 
        @Min(0) @Max(20000)
        @Schema(description = "Position in sequence", example = "10") 
        Integer n) {
        return buildSequence(n);
    }

    private String buildSequence(Integer n) {
        for (int key = 4; key <= n; key++) {
            int keyFinal = key;
            labSeqMap.computeIfAbsent(key, 
                value -> labSeqMap.get(keyFinal - 4).add(labSeqMap.get(keyFinal - 3)));
        }
        return labSeqMap.get(n).toString();
    }
}
