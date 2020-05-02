enum class AppBuildType(
    val minified: Boolean,
    val debuggable: Boolean,
    val signInConfig: String,
    val idSuffix: String,
    val nameSuffix: String
) {

    Debug(
        minified = false,
        debuggable = true,
        signInConfig = "debug",
        idSuffix = ".devel",
        nameSuffix = "-devel"
    ),
    Release(
        minified = true,
        debuggable = true,
        signInConfig = "release", // Ignored in this case
        idSuffix = "",
        nameSuffix = ""
    )
}
