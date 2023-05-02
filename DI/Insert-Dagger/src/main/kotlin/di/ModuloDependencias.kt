package di

import controllers.MyController
import dagger.Binds
import dagger.Component
import dagger.Module
import repositories.MyRepository
import repositories.Repository
import services.DataBaseService
import storage.MyStorageA
import storage.MyStorageB
import storage.Storage
import javax.inject.Named
import javax.inject.Singleton


@Module
// Puedo definir el módulo como una interfaz, pero no es necesario
interface DiModule {
    // Si no lo hace con anotaciones, que es lo más probable, nos creamos
    // el módulo como hemos hecho con Koin

    // Si no se lo indicamos Dagger no sabe que es un Singleton salvo que lo anote en la clase
    // @Singleton --> Lo coge de la clase!!!
    fun bindDataBaseService(impl: DataBaseService): DataBaseService

    @Singleton
    @Binds
    fun bindMyRepository(impl: MyRepository): Repository

    // @Singleton --> Así no es singleto, si tampoco lo tiene la clase
    @Binds
    @Named("MyStorageA")
    fun bindMyStorageA(impl: MyStorageA): Storage

    @Singleton
    @Binds
    @Named("MyStorageB")
    fun bindMyStorageB(impl: MyStorageB): Storage


}


// Este es el componente principal de la aplicación y es el que se encarga de
// dar acceso a las dependencias de la aplicación cuando las solicitamos

@Singleton
@Component(modules = [DiModule::class])
interface DI {
    fun getMyController(): MyController
}