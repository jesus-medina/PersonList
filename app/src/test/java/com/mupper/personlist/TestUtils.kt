package com.mupper.personlist

import io.mockk.MockK
import io.mockk.MockKDsl
import kotlin.reflect.KClass

inline fun <reified T : Any> relaxedMockk(
    name: String? = null,
    relaxed: Boolean = true,
    vararg moreInterfaces: KClass<*>,
    relaxUnitFun: Boolean = false,
    block: T.() -> Unit = {}
): T = MockK.useImpl {
    MockKDsl.internalMockk(
        name,
        relaxed,
        *moreInterfaces,
        relaxUnitFun = relaxUnitFun,
        block = block
    )
}