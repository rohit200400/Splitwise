package practice.project.Splitwise.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserMappingDTO {
    Integer grpId;
    List<Integer> userIds;
}
