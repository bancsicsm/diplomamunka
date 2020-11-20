set(CMAKE_C_COMPILER_WORKS 1)
project(uwb2020 C ASM)
cmake_minimum_required(VERSION 2.8)

SET(CMAKE_SYSTEM_NAME Generic)
SET(CMAKE_SYSTEM_PROCESSOR arm)

# Please create a 'CMakePaths.cmake' file to specify the variables:
#   - NRF5_SDK_ROOT
#   - NRFJPROG_ROOT
#   - ARM_GCC_ROOT
#   - TDOA_ROOT
include(CMakePaths.cmake)

SET(CMAKE_C_COMPILER   ${ARM_GCC_ROOT}/arm-none-eabi-gcc)
SET(CMAKE_ASM_COMPILER ${ARM_GCC_ROOT}/arm-none-eabi-gcc)

SET(CMAKE_SHARED_LIBRARY_LINK_C_FLAGS)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} \
    -DAPP_TIMER_V2 \
    -DAPP_TIMER_V2_RTC1_ENABLED \
    -DBOARD_CUSTOM \
    -DBSP_DEFINES_ONLY \
    -DCONFIG_GPIO_AS_PINRESET \
    -DFLOAT_ABI_HARD \
    -DNRF52 \
    -DNRF52832_XXAA \
    -DNRF52_PAN_74 \
    -DNRF_SD_BLE_API_VERSION=7 \
    -DS132 \
    -DDEBUG \
    -DSOFTDEVICE_PRESENT \
    -mcpu=cortex-m4 \
    -mthumb -mabi=aapcs \
    -Wall \
    -mfloat-abi=hard -mfpu=fpv4-sp-d16 \
    -ffunction-sections -fdata-sections -fno-strict-aliasing \
    -fno-builtin -fshort-enums \
    -std=c11 \
    -O3 -g3")

SET(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} \
    -O3 -g3  \
    -mthumb -mabi=aapcs \
    -T${PROJECT_SOURCE_DIR}/ld.script/ld_softdevice.ld -L${NRF5_SDK_ROOT}/modules/nrfx/mdk \
    -mcpu=cortex-m4 \
    -mfloat-abi=hard -mfpu=fpv4-sp-d16 \
    -Wl,--gc-sections")

add_definitions(-D__HEAP_SIZE=0 -D__STACK_SIZE=16384)


set(SRCS
      ${PROJECT_SOURCE_DIR}/main.c
      ${PROJECT_SOURCE_DIR}/log.c
      ${PROJECT_SOURCE_DIR}/crc.c
      ${PROJECT_SOURCE_DIR}/address_handler.c
      ${PROJECT_SOURCE_DIR}/dwm_utils.c
      ${PROJECT_SOURCE_DIR}/impl_observer.c
      ${PROJECT_SOURCE_DIR}/impl_anchor.c
      ${PROJECT_SOURCE_DIR}/impl_tag.c
      ${PROJECT_SOURCE_DIR}/uart.c
      ${PROJECT_SOURCE_DIR}/decadriver/deca_device.c
      ${PROJECT_SOURCE_DIR}/decadriver/deca_params_init.c
      ${PROJECT_SOURCE_DIR}/utils.c
      ${PROJECT_SOURCE_DIR}/timing.c
      ${PROJECT_SOURCE_DIR}/ranging.c
      ${PROJECT_SOURCE_DIR}/ranging_anchor.c
      ${PROJECT_SOURCE_DIR}/accel.c
      ${PROJECT_SOURCE_DIR}/data_format.c
      ${PROJECT_SOURCE_DIR}/ble/ble_func.c
      ${PROJECT_SOURCE_DIR}/ble/ranging_service.c
      ${PROJECT_SOURCE_DIR}/ble/accel_service.c

      ${TDOA_ROOT}/eml_rand_mt19937ar_stateful.c
      ${TDOA_ROOT}/find.c
      ${TDOA_ROOT}/isequal.c
      ${TDOA_ROOT}/kalmanfil_tdoa.c
      ${TDOA_ROOT}/minOrMax.c
      ${TDOA_ROOT}/rand.c
      ${TDOA_ROOT}/rtGetInf.c
      ${TDOA_ROOT}/rtGetNaN.c
      ${TDOA_ROOT}/rt_nonfinite.c
      ${TDOA_ROOT}/str2double.c
      ${TDOA_ROOT}/string1.c
      ${TDOA_ROOT}/tdoa_as_func_test.c
      ${TDOA_ROOT}/tdoa_as_func_test_data.c
      ${TDOA_ROOT}/tdoa_as_func_test_initialize.c
      ${TDOA_ROOT}/tdoa_as_func_test_terminate.c

      ${NRF5_SDK_ROOT}/modules/nrfx/mdk/gcc_startup_nrf52.S
      ${NRF5_SDK_ROOT}/components/libraries/log/src/nrf_log_backend_rtt.c
      ${NRF5_SDK_ROOT}/components/libraries/log/src/nrf_log_backend_serial.c
      ${NRF5_SDK_ROOT}/components/libraries/log/src/nrf_log_backend_uart.c
      ${NRF5_SDK_ROOT}/components/libraries/log/src/nrf_log_default_backends.c
      ${NRF5_SDK_ROOT}/components/libraries/log/src/nrf_log_frontend.c
      ${NRF5_SDK_ROOT}/components/libraries/log/src/nrf_log_str_formatter.c
      ${NRF5_SDK_ROOT}/components/libraries/button/app_button.c
      ${NRF5_SDK_ROOT}/components/libraries/util/app_error.c
      ${NRF5_SDK_ROOT}/components/libraries/util/app_error_handler_gcc.c
      ${NRF5_SDK_ROOT}/components/libraries/util/app_error_weak.c
      ${NRF5_SDK_ROOT}/components/libraries/scheduler/app_scheduler.c
      ${NRF5_SDK_ROOT}/components/libraries/timer/app_timer2.c
      ${NRF5_SDK_ROOT}/components/libraries/uart/app_uart_fifo.c
      ${NRF5_SDK_ROOT}/components/libraries/fifo/app_fifo.c
      ${NRF5_SDK_ROOT}/components/libraries/util/app_util_platform.c
      ${NRF5_SDK_ROOT}/components/libraries/timer/drv_rtc.c
      ${NRF5_SDK_ROOT}/components/libraries/hardfault/hardfault_implementation.c
      ${NRF5_SDK_ROOT}/components/libraries/hardfault/nrf52/handler/hardfault_handler_gcc.c
      ${NRF5_SDK_ROOT}/components/libraries/util/nrf_assert.c
      ${NRF5_SDK_ROOT}/components/libraries/atomic_fifo/nrf_atfifo.c
      ${NRF5_SDK_ROOT}/components/libraries/atomic_flags/nrf_atflags.c
      ${NRF5_SDK_ROOT}/components/libraries/atomic/nrf_atomic.c
      ${NRF5_SDK_ROOT}/components/libraries/balloc/nrf_balloc.c
      ${NRF5_SDK_ROOT}/external/fprintf/nrf_fprintf.c
      ${NRF5_SDK_ROOT}/external/fprintf/nrf_fprintf_format.c
      ${NRF5_SDK_ROOT}/components/libraries/memobj/nrf_memobj.c
      ${NRF5_SDK_ROOT}/components/libraries/pwr_mgmt/nrf_pwr_mgmt.c
      ${NRF5_SDK_ROOT}/components/libraries/ringbuf/nrf_ringbuf.c
      ${NRF5_SDK_ROOT}/components/libraries/experimental_section_vars/nrf_section_iter.c
      ${NRF5_SDK_ROOT}/components/libraries/sortlist/nrf_sortlist.c
      ${NRF5_SDK_ROOT}/components/libraries/strerror/nrf_strerror.c
      ${NRF5_SDK_ROOT}/modules/nrfx/mdk/system_nrf52.c
      ${NRF5_SDK_ROOT}/components/boards/boards.c
      ${NRF5_SDK_ROOT}/integration/nrfx/legacy/nrf_drv_clock.c
      ${NRF5_SDK_ROOT}/integration/nrfx/legacy/nrf_drv_uart.c
      ${NRF5_SDK_ROOT}/modules/nrfx/soc/nrfx_atomic.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_clock.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_gpiote.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/prs/nrfx_prs.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_uart.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_uarte.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_timer.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_spim.c
      ${NRF5_SDK_ROOT}/integration/nrfx/legacy/nrf_drv_spi.c
      ${NRF5_SDK_ROOT}/external/segger_rtt/SEGGER_RTT.c
      ${NRF5_SDK_ROOT}/external/segger_rtt/SEGGER_RTT_Syscalls_GCC.c
      ${NRF5_SDK_ROOT}/external/segger_rtt/SEGGER_RTT_printf.c
      ${NRF5_SDK_ROOT}/components/ble/common/ble_advdata.c
      ${NRF5_SDK_ROOT}/components/ble/common/ble_conn_params.c
      ${NRF5_SDK_ROOT}/components/ble/common/ble_conn_state.c
      ${NRF5_SDK_ROOT}/components/ble/common/ble_srv_common.c
      ${NRF5_SDK_ROOT}/components/ble/nrf_ble_gatt/nrf_ble_gatt.c
      ${NRF5_SDK_ROOT}/components/ble/nrf_ble_qwr/nrf_ble_qwr.c
      ${NRF5_SDK_ROOT}/external/utf_converter/utf.c
      ${NRF5_SDK_ROOT}/components/softdevice/common/nrf_sdh.c
      ${NRF5_SDK_ROOT}/components/softdevice/common/nrf_sdh_ble.c
      ${NRF5_SDK_ROOT}/components/softdevice/common/nrf_sdh_soc.c
      ${NRF5_SDK_ROOT}/components/drivers_ext/lis2dh12/lis2dh12.c
      ${NRF5_SDK_ROOT}/components/libraries/twi_sensor/nrf_twi_sensor.c
      ${NRF5_SDK_ROOT}/components/libraries/twi_mngr/nrf_twi_mngr.c
      ${NRF5_SDK_ROOT}/components/libraries/queue/nrf_queue.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_twi.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_saadc.c
      ${NRF5_SDK_ROOT}/integration/nrfx/legacy/nrf_drv_twi.c
      ${NRF5_SDK_ROOT}/modules/nrfx/drivers/src/nrfx_rtc.c
)

include_directories(
    ${PROJECT_SOURCE_DIR}
    ${PROJECT_SOURCE_DIR}/config
    ${PROJECT_SOURCE_DIR}/decadriver
    ${PROJECT_SOURCE_DIR}/ble

    ${TDOA_ROOT}

    ${NRF5_SDK_ROOT}/components/nfc/ndef/generic/message
    ${NRF5_SDK_ROOT}/components/nfc/t2t_lib
    ${NRF5_SDK_ROOT}/components/nfc/t4t_parser/hl_detection_procedure
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_ancs_c
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_ias_c
    ${NRF5_SDK_ROOT}/components/libraries/pwm
    ${NRF5_SDK_ROOT}/components/softdevice/s132/headers/nrf52
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/cdc/acm
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/hid/generic
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/msc
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/hid
    ${NRF5_SDK_ROOT}/modules/nrfx/hal
    ${NRF5_SDK_ROOT}/components/nfc/ndef/conn_hand_parser/le_oob_rec_parser
    ${NRF5_SDK_ROOT}/components/libraries/log
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_gls
    ${NRF5_SDK_ROOT}/components/libraries/fstorage
    ${NRF5_SDK_ROOT}/components/nfc/ndef/text
    ${NRF5_SDK_ROOT}/components/libraries/uart
    ${NRF5_SDK_ROOT}/components/libraries/fifo
    ${NRF5_SDK_ROOT}/components/libraries/mutex
    ${NRF5_SDK_ROOT}/components/libraries/gpiote
    ${NRF5_SDK_ROOT}/components/libraries/bootloader/ble_dfu
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/common
    ${NRF5_SDK_ROOT}/components/boards
    ${NRF5_SDK_ROOT}/components/nfc/ndef/generic/record
    ${NRF5_SDK_ROOT}/components/nfc/t4t_parser/cc_file
    ${NRF5_SDK_ROOT}/components/ble/ble_advertising
    ${NRF5_SDK_ROOT}/external/utf_converter
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_bas_c
    ${NRF5_SDK_ROOT}/modules/nrfx/drivers/include
    ${NRF5_SDK_ROOT}/components/libraries/experimental_task_manager
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_hrs_c
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/le_oob_rec
    ${NRF5_SDK_ROOT}/components/libraries/queue
    ${NRF5_SDK_ROOT}/components/libraries/pwr_mgmt
    ${NRF5_SDK_ROOT}/components/ble/ble_dtm
    ${NRF5_SDK_ROOT}/components/toolchain/cmsis/include
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_rscs_c
    ${NRF5_SDK_ROOT}/components/ble/common
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_lls
    ${NRF5_SDK_ROOT}/components/nfc/platform
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/ac_rec
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_bas
    ${NRF5_SDK_ROOT}/components/libraries/mpu
    ${NRF5_SDK_ROOT}/components/libraries/experimental_section_vars
    ${NRF5_SDK_ROOT}/components/softdevice/s132/headers
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_ans_c
    ${NRF5_SDK_ROOT}/components/libraries/slip
    ${NRF5_SDK_ROOT}/components/libraries/delay
    ${NRF5_SDK_ROOT}/components/libraries/csense_drv
    ${NRF5_SDK_ROOT}/components/libraries/memobj
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_nus_c
    ${NRF5_SDK_ROOT}/components/softdevice/common
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_ias
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/hid/mouse
    ${NRF5_SDK_ROOT}/components/libraries/low_power_pwm
    ${NRF5_SDK_ROOT}/components/nfc/ndef/conn_hand_parser/ble_oob_advdata_parser
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_dfu
    ${NRF5_SDK_ROOT}/external/fprintf
    ${NRF5_SDK_ROOT}/components/libraries/svc
    ${NRF5_SDK_ROOT}/components/libraries/atomic
    ${NRF5_SDK_ROOT}/components
    ${NRF5_SDK_ROOT}/components/libraries/scheduler
    ${NRF5_SDK_ROOT}/components/libraries/cli
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_hts
    ${NRF5_SDK_ROOT}/components/libraries/crc16
    ${NRF5_SDK_ROOT}/components/nfc/t4t_parser/apdu
    ${NRF5_SDK_ROOT}/components/libraries/util
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/cdc
    ${NRF5_SDK_ROOT}/components/libraries/csense
    ${NRF5_SDK_ROOT}/components/libraries/balloc
    ${NRF5_SDK_ROOT}/components/libraries/ecc
    ${NRF5_SDK_ROOT}/components/libraries/hardfault
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_cscs
    ${NRF5_SDK_ROOT}/components/libraries/hci
    ${NRF5_SDK_ROOT}/components/libraries/timer
    ${NRF5_SDK_ROOT}/integration/nrfx
    ${NRF5_SDK_ROOT}/components/nfc/t4t_parser/tlv
    ${NRF5_SDK_ROOT}/components/libraries/sortlist
    ${NRF5_SDK_ROOT}/components/libraries/spi_mngr
    ${NRF5_SDK_ROOT}/components/libraries/led_softblink
    ${NRF5_SDK_ROOT}/components/nfc/ndef/conn_hand_parser
    ${NRF5_SDK_ROOT}/components/libraries/sdcard
    ${NRF5_SDK_ROOT}/components/nfc/ndef/parser/record
    ${NRF5_SDK_ROOT}/modules/nrfx/mdk
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_cts_c
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_nus
    ${NRF5_SDK_ROOT}/components/libraries/twi_mngr
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_hids
    ${NRF5_SDK_ROOT}/components/libraries/strerror
    ${NRF5_SDK_ROOT}/components/libraries/crc32
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/ble_oob_advdata
    ${NRF5_SDK_ROOT}/components/nfc/t2t_parser
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/ble_pair_msg
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/audio
    ${NRF5_SDK_ROOT}/components/nfc/t4t_lib
    ${NRF5_SDK_ROOT}/components/ble/peer_manager
    ${NRF5_SDK_ROOT}/components/libraries/mem_manager
    ${NRF5_SDK_ROOT}/components/libraries/ringbuf
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_tps
    ${NRF5_SDK_ROOT}/components/nfc/ndef/parser/message
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_dis
    ${NRF5_SDK_ROOT}/components/nfc/ndef/uri
    ${NRF5_SDK_ROOT}/components/ble/nrf_ble_gatt
    ${NRF5_SDK_ROOT}/components/ble/nrf_ble_qwr
    ${NRF5_SDK_ROOT}/components/libraries/gfx
    ${NRF5_SDK_ROOT}/components/libraries/button
    ${NRF5_SDK_ROOT}/modules/nrfx
    ${NRF5_SDK_ROOT}/components/libraries/twi_sensor
    ${NRF5_SDK_ROOT}/integration/nrfx/legacy
    ${NRF5_SDK_ROOT}/components/libraries/usbd/class/hid/kbd
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/ep_oob_rec
    ${NRF5_SDK_ROOT}/external/segger_rtt
    ${NRF5_SDK_ROOT}/components/libraries/atomic_fifo
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_lbs_c
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/ble_pair_lib
    ${NRF5_SDK_ROOT}/components/libraries/crypto
    ${NRF5_SDK_ROOT}/components/ble/ble_racp
    ${NRF5_SDK_ROOT}/components/libraries/fds
    ${NRF5_SDK_ROOT}/components/nfc/ndef/launchapp
    ${NRF5_SDK_ROOT}/components/libraries/atomic_flags
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_hrs
    ${NRF5_SDK_ROOT}/components/ble/ble_services/ble_rscs
    ${NRF5_SDK_ROOT}/components/nfc/ndef/connection_handover/hs_rec
    ${NRF5_SDK_ROOT}/components/libraries/usbd
    ${NRF5_SDK_ROOT}/components/nfc/ndef/conn_hand_parser/ac_rec_parser
    ${NRF5_SDK_ROOT}/components/libraries/stack_guard
    ${NRF5_SDK_ROOT}/components/libraries/log/src

    ${NRF5_SDK_ROOT}/components/drivers_ext/lis2dh12
    ${NRF5_SDK_ROOT}/components/libraries/twi_sensor/
    ${NRF5_SDK_ROOT}/components/libraries/twi_mngr/
)


add_executable(${PROJECT_NAME}  ${SRCS})
target_link_libraries(${PROJECT_NAME} c nosys m)

add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
    COMMAND ${CMAKE_OBJCOPY} -O ihex $<TARGET_FILE:${PROJECT_NAME}> ${CMAKE_BINARY_DIR}/${PROJECT_NAME}.hex)

add_custom_target(flash
    COMMAND nrfjprog -f nrf52 --program ${PROJECT_NAME}.hex --sectorerase
    DEPENDS ${PROJECT_NAME})


add_custom_target(flash_softdevice
    COMMAND nrfjprog -f nrf52 --program ${PROJECT_SOURCE_DIR}/softdevice/s132_nrf52_7.0.1_softdevice.hex --sectorerase)

add_custom_target(gdbs
   COMMENT "Use 'netcat localhost 19021'"
   COMMAND JLinkGDBServer -if SWD -device nRF52)
