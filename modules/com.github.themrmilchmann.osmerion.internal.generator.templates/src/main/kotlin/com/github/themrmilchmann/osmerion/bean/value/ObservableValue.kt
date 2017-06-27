/*
 * Copyright (c) 2017 Leon Linhart,
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.themrmilchmann.osmerion.bean.value

import java.lang.reflect.Modifier

import com.github.themrmilchmann.osmerion.internal.generator.*
import com.github.themrmilchmann.osmerion.internal.generator.java.*

private fun name(type: PrimitiveType) = "Observable${type.abbrevName}Value"
fun ObservableValue(type: PrimitiveType) = if (types.contains(type)) Type(name(type), packageName) else throw IllegalArgumentException("")

val ObservableValue = Profile {
    types.forEach {
        val t_value = it

        javaInterface(name(t_value), packageName, MODULE_BASE, visibility = Modifier.PUBLIC) {
            addInterfaces(ParametrizedType("ObservableValue", packageName, t_value.boxedType.simpleName))

            documentation = "An observable {@code ${t_value.simpleName}} value."
            authors(AUTHOR_LEON_LINHART)
            since = VERSION_1_0_0

            t_value.method(
                "get",
                "Returns the value of this {@link ${this.fileName}}.",

                returnDoc = "the value of this {@code ${this.fileName}}",
                since = VERSION_1_0_0
            )

        }
    }
}