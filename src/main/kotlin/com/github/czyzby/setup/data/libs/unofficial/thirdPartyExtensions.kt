package com.github.czyzby.setup.data.libs.unofficial

import com.github.czyzby.setup.data.libs.Library
import com.github.czyzby.setup.data.platforms.*
import com.github.czyzby.setup.data.project.Project
import com.github.czyzby.setup.views.Extension

/**
 * Abstract base for unofficial extensions.
 * @author MJ
 */
abstract class ThirdPartyExtension : Library {
    override val official = false

    override fun initiate(project: Project) {
        project.properties[id + "Version"] = project.extensions.getVersion(id)
        initiateDependencies(project)
    }

    abstract fun initiateDependencies(project: Project)

    override fun addDependency(project: Project, platform: String, dependency: String) {
        if (dependency.endsWith(":sources")) {
            super.addDependency(project, platform, dependency.replace(":sources", ":\$${id}Version:sources"))
        } else {
            super.addDependency(project, platform, dependency + ":\$${id}Version")
        }
    }

    fun addExternalDependency(project: Project, platform: String, dependency: String) {
        super.addDependency(project, platform, dependency)
    }
}

/**
 * UI and level editor runtime.
 * @author UnderwaterApps
 */
@Extension
class Overlap2D : ThirdPartyExtension() {
    override val id = "overlap2d"
    override val defaultVersion = "0.1.1"
    override val url = "http://overlap2d.com/"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.underwaterapps.overlap2druntime:overlap2d-runtime-libgdx")
    }
}

/**
 * UI toolkit.
 * @author Kotcrab
 */
@Extension
class VisUI : ThirdPartyExtension() {
    override val id = "visUi"
    override val defaultVersion = "1.0.2"
    override val url = "https://github.com/kotcrab/VisEditor/wiki/VisUI"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.kotcrab.vis:vis-ui")

        addDependency(project, GWT.ID, "com.kotcrab.vis:vis-ui:sources")
        addGwtInherit(project, "com.kotcrab.vis.vis-ui")
    }
}

/**
 * VisEditor runtime.
 * @author Kotcrab
 */
@Extension
class VisRuntime : ThirdPartyExtension() {
    override val id = "visRuntime"
    override val defaultVersion = "0.3.1"
    override val url = "https://vis.kotcrab.com"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.kotcrab.vis:vis-runtime")

        addDependency(project, GWT.ID, "com.kotcrab.vis:vis-runtime-gwt")
        addDependency(project, GWT.ID, "com.kotcrab.vis:vis-runtime-gwt:sources")
        addDependency(project, GWT.ID, "com.kotcrab.vis:vis-runtime:sources")
        addGwtInherit(project, "com.kotcrab.vis.vis-runtime")

        project.properties["artemisVersion"] = "1.2.1"
        addExternalDependency(project, GWT.ID, "net.onedaybeard.artemis:artemis-odb-gwt:\$artemisVersion")
        addExternalDependency(project, GWT.ID, "net.onedaybeard.artemis:artemis-odb-gwt:\$artemisVersion:sources")
        addExternalDependency(project, GWT.ID, "net.onedaybeard.artemis:artemis-odb:\$artemisVersion:sources")
    }
}

/**
 * General LibGDX utilities.
 * @author Dermetfan
 */
@Extension
class LibgdxUtils : ThirdPartyExtension() {
    override val id = "utils"
    override val defaultVersion = "0.13.3"
    override val url = "http://dermetfan.net/libgdx-utils.php"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "net.dermetfan.libgdx-utils:libgdx-utils")

        addDependency(project, GWT.ID, "net.dermetfan.libgdx-utils:libgdx-utils:sources")
        addGwtInherit(project, "libgdx-utils")
    }
}

/**
 *
 * Box2D LibGDX utilities.
 * @author Dermetfan
 */
@Extension
class LibgdxUtilsBox2D : ThirdPartyExtension() {
    override val id = "utilsBox2d"
    override val defaultVersion = "0.13.3"
    override val url = "http://dermetfan.net/libgdx-utils.php"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "net.dermetfan.libgdx-utils:libgdx-utils-box2d")

        addDependency(project, GWT.ID, "net.dermetfan.libgdx-utils:libgdx-utils-box2d:sources")
        addGwtInherit(project, "libgdx-utils-box2d")

        LibgdxUtils().initiate(project)
    }
}

/**
 * Facebook graph API wrapper.
 * @author Tom Grill
 */
@Extension
class Facebook : ThirdPartyExtension() {
    override val id = "facebook"
    override val defaultVersion = "1.1.1"
    override val url = "https://github.com/TomGrill/gdx-facebook"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "de.tomgrill.gdxfacebook:gdx-facebook-core")

        addDependency(project, Android.ID, "de.tomgrill.gdxfacebook:gdx-facebook-android")

        addDependency(project, Desktop.ID, "de.tomgrill.gdxfacebook:gdx-facebook-desktop")

        addDependency(project, iOS.ID, "de.tomgrill.gdxfacebook:gdx-facebook-ios")
    }
}

/**
 * Native dialogs support.
 * @author Tom Grill
 */
@Extension
class Dialogs : ThirdPartyExtension() {
    override val id = "dialogs"
    override val defaultVersion = "1.0.0"
    override val url = "https://github.com/TomGrill/gdx-dialogs"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "de.tomgrill.gdxdialogs:gdx-dialogs-core")

        addDependency(project, Android.ID, "de.tomgrill.gdxdialogs:gdx-dialogs-android")

        addDependency(project, Desktop.ID, "de.tomgrill.gdxdialogs:gdx-dialogs-desktop")

        addDependency(project, iOS.ID, "de.tomgrill.gdxdialogs:gdx-dialogs-ios")
    }
}

/**
 * In-game console implementation.
 * @author StrongJoshua
 */
@Extension
class InGameConsole : ThirdPartyExtension() {
    override val id = "inGameConsole"
    override val defaultVersion = "0.3.3"
    override val url = "https://github.com/StrongJoshua/libgdx-inGameConsole"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.strongjoshua:libgdx-inGameConsole")

        addDependency(project, GWT.ID, "com.strongjoshua:libgdx-inGameConsole:sources")
        addGwtInherit(project, "com.strongjoshua.console")
    }
}

/**
 * Java Annotation Console Interface. In-game console implementation.
 * @author Yevgeny Krasik
 */
@Extension
class Jaci : ThirdPartyExtension() {
    override val id = "jaci"
    override val defaultVersion = "0.4.0"
    override val url = "https://github.com/ykrasik/jaci"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.ykrasik:jaci-libgdx-cli-java")
    }
}

/**
 * Java Annotation Console Interface. GWT-compatible in-game console implementation.
 * @author Yevgeny Krasik
 */
@Extension
class JaciGwt : ThirdPartyExtension() {
    override val id = "jaciGwt"
    override val defaultVersion = "0.4.0"
    override val url = "https://github.com/ykrasik/jaci"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.ykrasik:jaci-libgdx-cli-gwt")

        addDependency(project, GWT.ID, "com.github.ykrasik:jaci-libgdx-cli-gwt:sources")
        addGwtInherit(project, "com.github.ykrasik.jaci")
    }
}


/**
 * Version of Czyzby's libraries.
 * @author MJ
 */
const val AUTUMN_VERSION = "1.6.1.9.2"

/**
 * Guava-inspired LibGDX utilities.
 * @author MJ
 */
@Extension
class Kiwi : ThirdPartyExtension() {
    override val id = "kiwi"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/kiwi"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-kiwi")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-kiwi:sources")
        addGwtInherit(project, "com.github.czyzby.kiwi.GdxKiwi")
    }
}

/**
 * Parser of HTML-like templates that produces Scene2D widgets.
 * @author MJ
 */
@Extension
class LML : ThirdPartyExtension() {
    override val id = "lml"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/lml"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-lml")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-lml:sources")
        addGwtInherit(project, "com.github.czyzby.lml.GdxLml")

        Kiwi().initiate(project)
    }
}

/**
 * Parser of HTML-like templates that produces VisUI widgets.
 * @author MJ
 * @author Kotcrab
 */
@Extension
class LMLVis : ThirdPartyExtension() {
    override val id = "lmlVis"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/lml-vis"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-lml-vis")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-lml-vis:sources")
        addGwtInherit(project, "com.github.czyzby.lml.vis.GdxLmlVis")

        LML().initiate(project)
        VisUI().initiate(project)
    }
}

/**
 * Dependency injection mechanism with component scan using LibGDX reflection API.
 * @author MJ
 */
@Extension
class Autumn : ThirdPartyExtension() {
    override val id = "autumn"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/autumn"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-autumn")

        addDependency(project, Desktop.ID, "com.github.czyzby:gdx-autumn-fcs")

        addDependency(project, Headless.ID, "com.github.czyzby:gdx-autumn-fcs")

        addDependency(project, Android.ID, "com.github.czyzby:gdx-autumn-android")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-autumn:sources")
        addDependency(project, GWT.ID, "com.github.czyzby:gdx-autumn-gwt")
        addDependency(project, GWT.ID, "com.github.czyzby:gdx-autumn-gwt:sources")
        addGwtInherit(project, "com.github.czyzby.autumn.gwt.GdxAutumnGwt")

        Kiwi().initiate(project)
    }
}

/**
 * Model-view-controller framework on top of Autumn DI and LibGDX.
 * @author MJ
 */
@Extension
class AutumnMVC : ThirdPartyExtension() {
    override val id = "autumnMvc"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/mvc"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-autumn-mvc")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-autumn-mvc:sources")
        addGwtInherit(project, "com.github.czyzby.autumn.mvc.GdxAutumnMvc")

        LML().initiate(project)
        Autumn().initiate(project)
    }
}

/**
 * Cross-platform web sockets support.
 * @author MJ
 */
@Extension
class Websocket : ThirdPartyExtension() {
    override val id = "websocket"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/websocket"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-websocket")

        addDependency(project, Shared.ID, "com.github.czyzby:gdx-websocket")

        addDependency(project, Desktop.ID, "com.github.czyzby:gdx-websocket-common")
        addDependency(project, Headless.ID, "com.github.czyzby:gdx-websocket-common")
        addDependency(project, Android.ID, "com.github.czyzby:gdx-websocket-common")
        addDependency(project, iOS.ID, "com.github.czyzby:gdx-websocket-common")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-websocket:sources")
        addDependency(project, GWT.ID, "com.github.czyzby:gdx-websocket-gwt")
        addDependency(project, GWT.ID, "com.github.czyzby:gdx-websocket-gwt:sources")
        addGwtInherit(project, "com.github.czyzby.websocket.GdxWebSocketGwt")
    }
}

/**
 * Cross-platform efficient serialization without reflection.
 * @author MJ
 */
@Extension
class WebsocketSerialization : ThirdPartyExtension() {
    override val id = "websocketSerialization"
    override val defaultVersion = AUTUMN_VERSION
    override val url = "https://github.com/czyzby/gdx-lml/tree/master/websocket/natives/serialization"

    override fun initiateDependencies(project: Project) {
        addDependency(project, Core.ID, "com.github.czyzby:gdx-websocket-serialization")

        addDependency(project, Shared.ID, "com.github.czyzby:gdx-websocket-serialization")
        addDependency(project, Server.ID, "com.github.czyzby:gdx-websocket-serialization")

        addDependency(project, GWT.ID, "com.github.czyzby:gdx-websocket-serialization:sources")
        addGwtInherit(project, "com.github.czyzby.websocket.GdxWebSocketSerialization")

        Websocket().initiate(project)
    }
}