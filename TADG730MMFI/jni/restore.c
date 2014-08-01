#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>

#define command "mount -o rw,remount /system\nrm /system/framework/framework.odex\nmv /system/framework/framework.odex.bak /system/framework/framework.odex\nchmod 644 /system/framework/framework.odex\nrm /system/fonts/Roboto-Regular.ttf\nmv /system/fonts/Roboto-Regular.ttf.bak /system/fonts/Roboto-Regular.ttf\nchmod 644 /system/fonts/Roboto-Regular.ttf\nreboot"

int fileExists(const char* file) {
    struct stat buf;
    return (stat(file, &buf) == 0);
}

int main(int argc, char **argv) {
	char *arg=argv[1];	
	if(argc==2){
		if((arg[1])=='v'){
		printf("TAD G730 MMFI 1.0.0\n");
		exit(EXIT_SUCCESS);
		}
	}	

	if (getuid() != 0) {
		printf("This binary can only be executed with the help of su\n");
		printf("Invoke \"adb shell su -c restore\" to restore backup files\n");
		printf("Without su, you can only check the version of the binary\n");
		printf("Invoke \"adb shell restore -v\" to check version\n");
		exit(EXIT_FAILURE);
	}

	if(!fileExists("/system/framework/framework.odex.bak") || !fileExists("/system/fonts/Roboto-Regular.ttf.bak")){
		printf("There's no backup file to be restored\n");
		exit(EXIT_FAILURE);	
	}

	system(command);
   	
	exit(EXIT_SUCCESS);
}