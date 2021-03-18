/*
 * If not stated otherwise in this file or this component's LICENSE file the
 * following copyright and licenses apply:
 *
 * Copyright 2018 RDK Management
 *
 * Licensed under the Apache License, Version 2.0 (the License);
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
 */

package com.comcast.xconf.evaluators;

import com.comcast.apps.dataaccess.util.JsonUtil;
import com.comcast.apps.hesperius.ruleengine.domain.RuleUtils;
import com.comcast.apps.hesperius.ruleengine.domain.standard.BaseEvaluator;
import com.comcast.apps.hesperius.ruleengine.domain.standard.StandardFreeArgType;
import com.comcast.xconf.estbfirmware.factory.RuleFactory;
import com.comcast.xconf.rfc.PercentRange;
import com.comcast.xconf.utils.PercentRangeParser;
import org.springframework.stereotype.Component;

@Component
public class PercentRangeEvaluator extends BaseEvaluator {

    public PercentRangeEvaluator() {
        super(StandardFreeArgType.STRING, RuleFactory.RANGE, String.class);
    }

    @Override
    protected boolean evaluateInternal(String macAddress, Object fixedArgValue) {
        // System.out.println("#### PercentRangeEvaluator.evaluateInternal() ####");
        PercentRange percentRange = PercentRangeParser.parsePercentRange((String) fixedArgValue);
        // percentageRage = PercentRange{, startRange=0.0, endRange=50.0}
        return percentRange.getStartRange() >= 0 && percentRange.getEndRange() > 0
                && !RuleUtils.fitsPercent(JsonUtil.toJson(macAddress), percentRange.getStartRange())
                && RuleUtils.fitsPercent(JsonUtil.toJson(macAddress), percentRange.getEndRange());
    }
}
