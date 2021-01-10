/*
 * This file is part of LuckPerms, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
// Taken from 
// * https://github.com/lucko/LuckPerms/blob/505c073c8e9b9a841e7267b34f3e42a84d0469d3/bukkit/src/main/java/me/lucko/luckperms/bukkit/BukkitSchedulerAdapter.java
// * https://github.com/lucko/LuckPerms/blob/505c073c8e9b9a841e7267b34f3e42a84d0469d3/common/src/main/java/me/lucko/luckperms/common/plugin/scheduler/AbstractJavaScheduler.java

package org.sweetiebelle.lib.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

final class ErrorReportingExecutor implements Executor {

    final ExecutorService delegate;

    ErrorReportingExecutor(ExecutorService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void execute(Runnable command) {
        this.delegate.execute(new ErrorReportingRunnable(command));
    }
}