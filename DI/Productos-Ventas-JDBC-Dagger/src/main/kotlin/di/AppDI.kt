package di

import config.AppConfig
import controllers.ProductosController
import controllers.VentasController
import dagger.Binds
import dagger.Component
import dagger.Module
import repositories.productos.ProductosRepository
import repositories.productos.ProductosRepositoryImpl
import repositories.ventas.VentasRepository
import repositories.ventas.VentasRepositoryImpl
import services.storage.productos.ProductosFicheroCsvService
import services.storage.productos.ProductosFicheroJsonService
import services.storage.productos.ProductosStorageService
import services.storage.ventas.VentasFicheroJsonService
import services.storage.ventas.VentasStorageService
import javax.inject.Named
import javax.inject.Singleton


// Modulo de Dependencias no calculadas con anotaciones de Productos
// Esto es así por usar interfaces y no clases concretas

@Module
interface ProductosModule {
    // No devuelvo nada, solo bindeo el objeto,por eso no privider
    @Singleton
    @Binds
    fun bindProductosRepository(impl: ProductosRepositoryImpl): ProductosRepository

    @Singleton
    @Binds
    @Named("ProductosCsvService")
    fun bindCsvProductosService(impl: ProductosFicheroCsvService): ProductosStorageService

    @Singleton
    @Binds
    @Named("ProductosJsonService")
    fun bindJsonProductosService(impl: ProductosFicheroJsonService): ProductosStorageService

}

// Modulo de Dependencias no calculadas con anotaciones de Ventas
// Esto es así por usar interfaces y no clases concretas

@Module
interface VentasModule {
    // No devuelvo nada, solo bindeo el objeto,por eso no privider
    @Singleton
    @Binds
    fun bindVentasRepository(impl: VentasRepositoryImpl): VentasRepository

    @Singleton
    @Binds
    fun bindVentasStorage(impl: VentasFicheroJsonService): VentasStorageService
}


// Este es el componente principal de la aplicación y es el que se encarga de
// dar acceso a las dependencias de la aplicación cuando las solicitamos

@Singleton
@Component(modules = [VentasModule::class, ProductosModule::class])
interface DI {
    fun getVentasController(): VentasController
    fun getProductosController(): ProductosController
    fun getAppConfig(): AppConfig
}