#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){

	int n = 75000;
	char alphanumeric[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	char passwordGenerated[n];
	char res[n];
	int i, j;
	
	
	/**
	* Generer le mot
	*/
	for(i=0; i<n; i++){
//		printf("%d\n", i);
		int r = rand()%(sizeof(alphanumeric));

		char c = alphanumeric[r];
//		printf("c = %c\n", c);
		passwordGenerated[i]= c;
	}
	
	printf("*** MOT DE PASSE DE GÉNÉRÉ ***\n");
	for(i = 0; i < sizeof(passwordGenerated); i++){
		printf("%c", passwordGenerated[i]);
	}
	printf("\n");
	 

	/**
	* la recherche du mot equivalent
	*/
	for(i = 0; i < sizeof(passwordGenerated); i++){

		for(j = 0; j < sizeof(alphanumeric); j++){
			if(alphanumeric[j] == passwordGenerated[i]){
				char c = alphanumeric[i];
				res[i] = alphanumeric[j];
			}
		}
	}
	printf("*** MOT DE PASSE DE RETROUVÉ ***\n");
	for(i = 0; i < sizeof(res); i++){
		printf("%c", res[i]);
	}
	printf("\n");
		
	return 0;
}
