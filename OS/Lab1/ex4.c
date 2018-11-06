#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

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
		scanf("%s", user_enter);
		execl("/bin/ls", "-l", user_enter, NULL);
	}
	else{
		wait(&status);
		printf("This parent process\n");
		execl("./a.out", user_enter, NULL);
		puts("Program ls has terminated");
	}
	return 0;
}