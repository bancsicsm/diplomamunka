# UWB firmware

## Fordítási instrukciók
A szoftver fordítására a CMake program használható (tesztelt változat 3.10.2). A fejlesztőkörnyezet egyelőre kizárólag Linux környezetet támogat. A fordításhoz három dolog beszerzésére lesz szükség:

* NRF5 17.0.0 SDK ([https://www.nordicsemi.com/Software-and-tools/Software/nRF5-SDK/Download#infotabs](https://www.nordicsemi.com/Software-and-tools/Software/nRF5-SDK/Download#infotabs))
* ARM GCC fordító ([https://developer.arm.com/tools-and-software/open-source-software/developer-tools/gnu-toolchain/gnu-rm/downloads](https://developer.arm.com/tools-and-software/open-source-software/developer-tools/gnu-toolchain/gnu-rm/downloads))
* NRFJPROG és JLink ([https://www.nordicsemi.com/Software-and-tools/Development-Tools/nRF-Command-Line-Tools/Download#infotabs](https://www.nordicsemi.com/Software-and-tools/Development-Tools/nRF-Command-Line-Tools/Download#infotabs))

A fordításhoz első lépésben csomagoljuk ki a fenti programokat könyvtárakba. Az `nrfjprog` esetében a `*.deb` fájlokat telepítsük, például:

        sudo dpkg -i nRF-Command-Line-Tools_10_6_0_Linux-amd64.deb
        sudo dpkg -i JLink_Linux_V660e_x86_64.deb

Ezek után hozzuk létre a projektben található `CMakeLists.txt` fájl mellé egy `CMakePaths.cmake` fájlt, melyben állítsuk be az alapvető elérési útvonalakat, pl.:

        set(NRF5_SDK_ROOT  ~/software/sdk/nRF5)
        set(ARM_GCC_ROOT  ~/software/sdk/gcc-arm-none-eabi-9-2019-q4-major/bin)

Természetesen itt a helyileg kicsomagolt szoftverek útvonalait kell behelyettesíteni. Ezután a szoftver fordítható a CMake szoftver segítségével, például:

        mkdir /tmp/build-fw
        cd /tmp/build-fw
        cmake -S <firmware könyvtár elérési útvonala> -B <célkönyvtár>
        make

Ha a fordítás sikerül, akkor létrejön egy `uwb2020` nevű ELF és HEX fájl. A szoftver feltöltése elvégezhető a

        make flash

paranccsal. Amennyiben hibakeresést indítunk, azt megtehetjük a

        make gdbs

paranccsal. Ekkor a hibakeresési üzeneteket, amelyeket a szoftver küld, az 19021-es TCP porton érhetjük el, azaz egy másik parancssorból például a `netcat` program segítségével:

        netcat localhost 19021

## Softdevice
Fontos kiemelni, hogy a firmware működéséhez szükség van az S132-es Nordic softdevice-ra is. Ezt a

        make flash_softdevice

paranccsal tudjuk feltölteni az eszközre.
