/**
 * Copyright 2014 Netflix, Inc.
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
 */
package rx.cut.rxjava.internal.operators;

import rx.cut.rxjava.Observable;
import rx.cut.rxjava.Observable.Operator;
import rx.cut.rxjava.Subscriber;
import rx.cut.rxjava.functions.Action0;
import rx.cut.rxjava.subscriptions.Subscriptions;

/**
 * This operator modifies an {@link Observable} so a given action is invoked when the {@link Observable} is unsubscribed.
 * @param <T> The type of the elements in the {@link Observable} that this operator modifies
 */
public class OperatorDoOnUnsubscribe<T> implements Operator<T, T> {
    private final Action0 unsubscribe;

    /**
     * Constructs an instance of the operator with the callback that gets invoked when the modified Observable is unsubscribed
     * @param unsubscribe The action that gets invoked when the modified {@link Observable} is unsubscribed
     */
    public OperatorDoOnUnsubscribe(Action0 unsubscribe) {
        this.unsubscribe = unsubscribe;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        child.add(Subscriptions.create(unsubscribe));

        // Pass through since this operator is for notification only, there is
        // no change to the stream whatsoever.
        return child;
    }
}
