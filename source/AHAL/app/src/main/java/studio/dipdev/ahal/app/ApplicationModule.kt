package studio.dipdev.ahal.app

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule internal constructor(private val application: AHALApp) {

    @Provides
    internal fun provideApplication(): AHALApp {
        return application
    }

    @Provides
    internal fun provideContext(): Context {
        return application.baseContext
    }
}
