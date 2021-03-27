/*******************************************************************************
 * If not stated otherwise in this file or this component's Licenses.txt file the
 * following copyright and licenses apply:
 *
 * Copyright 2018 RDK Management
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.comcast.xconf.evaluators;

import com.comcast.apps.dataaccess.util.JsonUtil;
import com.comcast.apps.hesperius.ruleengine.domain.standard.StandardFreeArgType;
import com.comcast.apps.hesperius.ruleengine.main.api.FixedArg;
import com.comcast.apps.hesperius.ruleengine.main.api.FreeArg;
import com.comcast.apps.hesperius.ruleengine.main.impl.Condition;
import com.comcast.xconf.BaseTestUtils;
import com.comcast.xconf.estbfirmware.factory.RuleFactory;
import com.comcast.xconf.rfc.PercentRange;
import com.comcast.xconf.utils.PercentRangeParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchOperationEvaluatorTest {

    @Test
    public void test105() throws Exception {
        PercentRangeEvaluator ev = new PercentRangeEvaluator();
        String m1 = "B4:F2:E8:15:67:46";
        Object r1 = "0-50";
        boolean b1 = ev.evaluateInternal(m1, r1);
        System.out.println("m1 = " + m1 + ", r1 = " + r1 + ", b1 = " + b1);

        r1 = "0-99";
        b1 = ev.evaluateInternal(m1, r1);
        System.out.println("m1 = " + m1 + ", r1 = " + r1 + ", b1 = " + b1);

        r1 = "0-1";
        b1 = ev.evaluateInternal(m1, r1);
        System.out.println("m1 = " + m1 + ", r1 = " + r1 + ", b1 = " + b1);
    }

    @Test
    public void test106() throws Exception {
        PercentRangeEvaluator ev = new PercentRangeEvaluator();
        Object r1 = "0-50";

        List<String> macs = Arrays.asList(
                "04:4E:5A:CE:A7:08",
                "04:81:9B:68:9F:A3",
                "08:7E:64:FD:E5:27",
                "11:22:33:44:55:66",
                "14:c0:3e:7e:45:15",
                "24:A7:DC:FF:9D:E3",
                "24:A7:DC:FF:A2:E3",
                "24:A7:DC:FF:A5:8B",
                "24:A7:DC:FF:AE:13",
                "24:A7:DC:FF:AF:43",
                "3C:7A:8A:37:6C:8A",
                "3C:7A:8A:5B:1E:D2",
                "3C:7A:8A:5C:22:28",
                "44:AA:F5:9D:0F:3A",
                "48:1D:70:14:4A:6F",
                "5C:E3:0E:50:E5:F7",
                "5c:8F:E0:A1:BD:7E",
                "70:4F:B8:40:4B:2A",
                "AC:B3:13:02:F7:B2",
                "AC:DB:48:04:DA:3E",
                "B4:2A:0E:46:4D:5B",
                "C0:89:AB:EE:87:B2",
                "DC:EB:69:5C:75:56",
                "DC:EB:69:5C:78:12",
                "F8:A0:97:EF:09:1F",
                "FC:91:14:53:04:21"
        );
        for (String m1 : macs) {
            boolean b1 = ev.evaluateInternal(m1, r1);
            System.out.println("m1 = " + m1 + ", r1 = " + r1 + ", b1 = " + b1);
        }
    }
/*
m1 = 04:4E:5A:CE:A7:08, r1 = 0-50, b1 = false
m1 = 04:81:9B:68:9F:A3, r1 = 0-50, b1 = false
m1 = 08:7E:64:FD:E5:27, r1 = 0-50, b1 = false
m1 = 11:22:33:44:55:66, r1 = 0-50, b1 = true
m1 = 14:c0:3e:7e:45:15, r1 = 0-50, b1 = true
m1 = 24:A7:DC:FF:9D:E3, r1 = 0-50, b1 = true
m1 = 24:A7:DC:FF:A2:E3, r1 = 0-50, b1 = true
m1 = 24:A7:DC:FF:A5:8B, r1 = 0-50, b1 = true
m1 = 24:A7:DC:FF:AE:13, r1 = 0-50, b1 = false
m1 = 24:A7:DC:FF:AF:43, r1 = 0-50, b1 = false
m1 = 3C:7A:8A:37:6C:8A, r1 = 0-50, b1 = false
m1 = 3C:7A:8A:5B:1E:D2, r1 = 0-50, b1 = true
m1 = 3C:7A:8A:5C:22:28, r1 = 0-50, b1 = true
m1 = 44:AA:F5:9D:0F:3A, r1 = 0-50, b1 = true
m1 = 48:1D:70:14:4A:6F, r1 = 0-50, b1 = false
m1 = 5C:E3:0E:50:E5:F7, r1 = 0-50, b1 = true
m1 = 5c:8F:E0:A1:BD:7E, r1 = 0-50, b1 = true
m1 = 70:4F:B8:40:4B:2A, r1 = 0-50, b1 = false
m1 = AC:B3:13:02:F7:B2, r1 = 0-50, b1 = false
m1 = AC:DB:48:04:DA:3E, r1 = 0-50, b1 = true
m1 = B4:2A:0E:46:4D:5B, r1 = 0-50, b1 = false
m1 = C0:89:AB:EE:87:B2, r1 = 0-50, b1 = true
m1 = DC:EB:69:5C:75:56, r1 = 0-50, b1 = false
m1 = DC:EB:69:5C:78:12, r1 = 0-50, b1 = false
m1 = F8:A0:97:EF:09:1F, r1 = 0-50, b1 = true
m1 = FC:91:14:53:04:21, r1 = 0-50, b1 = false
 */

    @Test
    public void test104() throws Exception {
        Object fixedArgValue = "0-50";
        PercentRange percentRange = PercentRangeParser.parsePercentRange((String) fixedArgValue);
        System.out.println("percentageRage = " + percentRange);
    }

    @Test
    public void test103() throws Exception {
        String m1 = "B4:F2:E8:15:67:46";
        String s1 = JsonUtil.toJson(m1);
        System.out.println("s1 = " + s1); // s1 = "B4:F2:E8:15:67:46"
        if (m1.equals(s1)) {
            System.out.println("m1 == s1");
        } else {
            System.out.println("F01 m1 = " + m1);
            System.out.println("F02 s1 = " + s1);
        }
    }

    @Test
    public void evaluateInternalPositiveCases() throws Exception {
        Condition condition = BaseTestUtils.createCondition(
                RuleFactory.MODEL,
                RuleFactory.MATCH,
                FixedArg.from("AAA?A"));

        Map<String, String> context = new HashMap<>();
        context.put("model", "AAABA");
        MatchOperationEvaluator evaluator = new MatchOperationEvaluator();

        assertTrue(evaluator.evaluate(condition, context));

        context.put("model", "AAABAA");

        assertFalse(evaluator.evaluate(condition, context));

        condition = BaseTestUtils.createCondition(
                new FreeArg(StandardFreeArgType.STRING, "firmwareVersion"),
                RuleFactory.MATCH,
                FixedArg.from("version.aa.*"));
        context.put("firmwareVersion", "version.aa.aa.dddd");

        assertTrue(evaluator.evaluate(condition, context));

        context.put("firmwareVersion", "version.a");

        assertFalse(evaluator.evaluate(condition, context));
    }

}
