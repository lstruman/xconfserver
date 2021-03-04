/*
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
 *
 * Author: slavrenyuk
 * Created: 6/6/14
 */
package com.comcast.apps.hesperius.ruleengine;

import com.comcast.apps.hesperius.ruleengine.domain.RuleEngineConfig;
import com.comcast.apps.hesperius.ruleengine.domain.additional.AuxEvaluators;
import com.comcast.apps.hesperius.ruleengine.domain.standard.StandardEvaluators;
import com.comcast.apps.hesperius.ruleengine.main.api.*;
import com.comcast.apps.hesperius.ruleengine.main.impl.Evaluators;
import com.comcast.apps.hesperius.ruleengine.main.impl.RuleProcessor;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public final class RuleEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngine.class);
    public static final Evaluators RULE_EVALUATORS;

    static {
        RULE_EVALUATORS = StandardEvaluators.get();
        System.out.println("A01 re.size() = " + RULE_EVALUATORS.size()); // ==> size=4
        RULE_EVALUATORS.verbose();
        System.out.println("---- break line in static{} ----");
        RULE_EVALUATORS.add(AuxEvaluators.get());
        RULE_EVALUATORS.verbose();
        System.out.println("A02 re.size() = " + RULE_EVALUATORS.size()); // ==> size=7

        final RuleEngineConfig config = RuleEngineConfig.Provider.INSTANCE.getConfig();
        // JAMES in my test, the config is empty
        for (String className : config.getEvaluatorClasses()) {
            System.out.println("### C01 className = " + className);
            try {
                final Class evaluatorClass = Class.forName(className);
                LOGGER.info("registering {} as customEvaluator", evaluatorClass.getSimpleName());
                if (Evaluators.class.isAssignableFrom(evaluatorClass)) {
                    RULE_EVALUATORS.add((Evaluators) evaluatorClass.newInstance(), config.isAllowDefaultOperationsOverrides());
                } else if (IConditionEvaluator.class.isAssignableFrom(evaluatorClass)) {
                    RULE_EVALUATORS.add((IConditionEvaluator) evaluatorClass.newInstance(), config.isAllowDefaultOperationsOverrides());
                } else {
                    LOGGER.error("inappropriate type supplied as evaluators source {}, must be either {} or {}", className);
                }
            } catch (ClassNotFoundException ex) {
                LOGGER.error("could not locate " + className + "in classpath", ex);
            } catch (InstantiationException e) {
                LOGGER.error("could not instantiate " + className, e);
            } catch (IllegalAccessException e) {
                LOGGER.error("could not instantiate " + className, e);
            }
        }
        System.out.println("A03 re.size() = " + RULE_EVALUATORS.size()); // ==> size=7
    }

    /**
     * Currently by default standard and auxiliary evaluators
     */
    public static <T extends ICondition, U extends IRule<T, U>> IRuleProcessor<T, U> getRuleProcessor() {
        return new RuleProcessor<T, U>(RULE_EVALUATORS);
    }

    public static final Set<Operation> getSupportedOperations() {
        return FluentIterable.from(RULE_EVALUATORS)
                .transform(new Function<IConditionEvaluator, Operation>() {
                    @Override
                    public Operation apply(IConditionEvaluator input) {
                        return input.getOperation();
                    }
                }).toSet();
    }
}
