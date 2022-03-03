package hu.szokemate.myfortnightly.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class ViewModelTestListener : TestListener {

    private val testDispatcher: TestCoroutineDispatcher =
        TestCoroutineDispatcher()

    override suspend fun beforeTest(testCase: TestCase) {
        @Suppress("DEPRECATION")
        Dispatchers.setMain(testDispatcher)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        @Suppress("DEPRECATION")
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    override suspend fun beforeSpec(spec: Spec) {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    override suspend fun afterSpec(spec: Spec) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}