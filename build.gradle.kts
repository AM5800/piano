buildscript {
    repositories {
        gradleScriptKotlin()
        jcenter()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
    plugin<ApplicationPlugin>()
}

repositories {
    gradleScriptKotlin()
}

dependencies {
    compile(kotlinModule("stdlib"))
    compile("joda-time:joda-time:2.2")
    compile("com.google.guava", "guava", "18.0")
    compile("org.jscience:jscience:4.3.1")
    compile("junit:junit:4.12")
}