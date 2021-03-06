package org.openrndr.draw

import org.openrndr.internal.Driver

data class ShadeStructure(var uniforms: String? = null,
                          var attributes: String? = null,
                          var vertexTransform: String? = null,
                          var fragmentTransform: String? = null,
                          var vertexPreamble: String? = null,
                          var fragmentPreamble: String? = null,
                          var outputs: String? = null,
                          var varyingOut: String? = null,
                          var varyingIn: String? = null,
                          var varyingBridge: String? = null,
                          var suppressDefaultOutput: Boolean = false
)

abstract class ShadeStyleManager(val name: String) {
    companion object {
        fun fromGenerators(name: String, vertexShaderGenerator: (ShadeStructure) -> String, fragmentShaderGenerator: (ShadeStructure) -> String): ShadeStyleManager {
            return Driver.instance.createShadeStyleManager(name, vertexShaderGenerator, fragmentShaderGenerator)
        }
    }

    abstract fun shader(style: ShadeStyle?, vertexFormats: List<VertexFormat>, instanceFormats: List<VertexFormat> = emptyList()): Shader
    fun shader(style: ShadeStyle?, format: VertexFormat): Shader = shader(style, listOf(format), emptyList())
}