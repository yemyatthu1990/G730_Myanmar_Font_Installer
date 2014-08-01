LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := restore
LOCAL_SRC_FILES := restore.c

include $(BUILD_EXECUTABLE)
