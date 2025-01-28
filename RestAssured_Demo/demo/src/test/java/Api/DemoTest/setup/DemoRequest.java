package Api.DemoTest.setup;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemoRequest {

    private String name;
    private String job;

    public static DemoRequest createDemoRequest(){
        return DemoRequest.builder()
        .name("JUAN")
        .job("QA")
        .build()
        ;
    }

}
