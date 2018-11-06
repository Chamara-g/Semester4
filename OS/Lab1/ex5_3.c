#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{

	char user_enter[20];
	int pid;
	int status;

	pid = fork();

	printf("PID of this c file (ex4.c file) = %d\n", getpid());

	if(pid < -1){
		perror("fork");
		exit(1);	
	}
	if(pid == 0){
		printf("This the child process\n");
		execl("./server", "", NULL);
	}
	else{
		wait(&status);
		printf("This parent process\n");
		execl("./thread", user_enter, NULL);
		puts("server has terminated");
	}
	return 0;
}