package studio.dipdev.ahal.app

import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AHALApp)

    companion object {
        fun init(application: AHALApp): ApplicationComponent {
            return DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
        }
    }
}
