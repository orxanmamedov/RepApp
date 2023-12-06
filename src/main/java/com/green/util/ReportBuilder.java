package com.green.util;

import com.green.entity.Activity;
import com.green.entity.Member;

import java.util.List;
import java.util.Map;

public class ReportBuilder {

    public String report(){
        Map<Member, List<Activity>> map = DataFetch.takeFromDb();
        StringBuilder output = new StringBuilder();
        // Iterate over the map entries
        for (Map.Entry<Member, List<Activity>> entry : map.entrySet()) {
            Member key = entry.getKey();
            List<Activity> value = entry.getValue();


            output.append("Member: ").append(key.getName()).append("\n");


            for (Activity activity : value) {

                output.append("  Subject: ").append(activity.getSubject())
                        .append(", Took Time: ").append(activity.getTookTime()).append("h")
                        .append("\n");
            }
        }

        return output.toString();
    }
}
