//Mpekos Dimitrios AM:2108
//Vasiliadis Agisilaos AM:2023
//produce middle code
//PARAKALOUME NA DIAVASTEI TO README   DISTIXWS LEITOURGEI MONO GIA 1 ARXEIO KAI OXI GIA 5
//To programma einai grammeno se c++
//Leitourgoun ta pada swsta ektos apo tin exit

/*
Gia tin paragwgi ektelesimou:		g++ middle.cpp
Gia tin ektelesi:			a.out test.st
Meta apo afta paragetai ena arxeio me onoma 2108_2023_test1.int  */

#include <sstream>
#include <iostream>
#include <cstdlib>
#include <stdlib.h>
#include <cstdio>
#include <cmath>
#include <list>
#include <cstring>

// variables and constants  
#define variable_tk 1
#define num_tk 2
// defined words
#define and_tk 3
#define declare_tk 4
#define do_tk 5
#define else_tk 6
#define end_declare_tk 7
#define exit_tk 8
#define procedure_tk 9
#define function_tk 10
#define print_tk 11
#define incase_tk 12
#define if_tk 13
#define in_tk 14
#define inout_tk 15
#define not_tk 16
#define forcase_tk 17
#define program_tk 18
#define or_tk 19
#define return_tk 20
#define while_tk 21 
#define copy_tk 22
#define when_tk 23
#define call_tk 24
//numerical operators
#define plus_tk 30
#define minus_tk 31
#define multiply_tk 32
#define divide_tk 33
//relational operators
#define smaller_tk 40
#define greater_tk 41
#define equal_tk 42
#define smaller_or_equal_tk 43
#define greater_or_equal_tk 44
#define not_equal_tk 45
//assignment
#define assignment_tk 46
//seperators
#define semicolon_tk 47
#define comma_tk 48
//grouping symbols
#define left_bracket_tk 50
#define right_bracket_tk 51
#define left_parenthesis_tk 52
#define right_parenthesis_tk 53
#define left_square_bracket_tk 54
#define right_square_bracket_tk 55
//comment symbols
#define begin_comment_tk 60
#define end_comment_tk 61




using namespace std;

char* token;
FILE *fp,*fw;
int token_id;
int counter_line=1;
int command_counter=90,temp_counter=0,new_name=0;
int strangeFile=1;
int nestingLevel=0;
	

class quadra{
	private:
		int id;
		string string1,string2,string3,string4;
	public:
		void genQuad(string op,string x,string y,string z);
		int getid();
		void set_s4(string z);
		string getstring1();
		string getstring2();
		string getstring3();
		string getstring4();
};

void quadra::genQuad(string op,string x,string y,string z){
	string1=op;
	string2=x;
	string3=y;
	string4=z;
	id=command_counter;
}

int quadra::getid(){
	return(id);
}

string quadra::getstring1(){
	return(string1);
}

string quadra::getstring2(){
	return(string2);
}
string quadra::getstring3(){
	return(string3);
}
string quadra::getstring4(){
	return(string4);
}
void quadra::set_s4(string z){
	string4=z;
}

class idList{
	private:
		list<int> True;
		list<int> False;
	public:
		list<int> getFalseList();
		list<int> getTrueList();
		void addToTrue(int x);
		void addToFalse(int x);
		void setTrue(list<int> x);
		void setFalse(list<int> x);
};

list<int> idList::getFalseList(){
	return(False);
}

void idList::addToFalse(int x){
	False.push_back(x);
}

list<int> idList::getTrueList(){
	return(True);
}

void idList::addToTrue(int x){
	True.push_back(x);
}

void idList::setTrue(list<int> x){
	True=x;	
}

void idList::setFalse(list<int> x){
	False=x	;
}

class argument{
	private:
		string parMode;
	public:
		void setParMode(string a);
		string getParMode();
};

void argument:: setParMode(string a){
	parMode=a;
}

string argument:: getParMode(){
	return(parMode);
}


class entity{
	private:
		string name;
		string entityType;
		string type; 
		int offset; 
		int startQuad;
		list<argument> arg;
		int frameLength;
		string parMode;  

	public:	
		
		void setType(string a);	
		void setName(string a);
		void setEntityType(string a);
		void setOffset(int a);
		void setStartQuad(int a);
		void setFrameLength(int a);
		void setParMode(string a);
		void setArgList(list<argument> a);
		void addToArgument(argument a);
		string getName();
		string getType();
		string getEntityType();
		int getOffset();
		int getStartQuad();
		int getFrameLength();
		string getParMode();
		list<argument> getArgList();
};

void entity:: setType(string a){
	type=a;
}

string entity:: getType(){
	return(type);
}

void entity:: setName(string a){
	name=a;
}

void entity:: setEntityType(string a){
	entityType=a;
}

void entity:: setOffset(int a){
	offset=a;
}

void entity:: setStartQuad(int a){
	startQuad=a;
}

void entity:: setFrameLength(int a){
	frameLength=a;
}

void entity:: setParMode(string a){
	parMode=a;
}

void entity:: setArgList(list<argument> a){
	arg=a;
}

void entity:: addToArgument(argument a){
	arg.push_back(a);
}
string entity:: getName(){
	return(name);
}

string entity:: getEntityType(){
	return(entityType);
}

int entity:: getOffset(){
	return(offset);
}

int entity:: getStartQuad(){
	return(startQuad);
}

int entity:: getFrameLength(){
	return(frameLength);
}

string entity:: getParMode(){
	return(parMode);
}

list<argument> entity:: getArgList(){
	return(arg);
}


class scope{
	private:
		string name;
		list<entity> entList;
		int nestingLevel;
		int tempOffset;
	public:
		void addToEntList(entity a);
		void setOffset(int a);
		void offsetPlusFour();
		int getOffset();
		void setName(string a);
		string getName();
		void setNestingLevel(int a);
		int getNestingLevel();
		list<entity> getEntList();
		void setEntList(list<entity> a);
		void addToEntity(entity a);
};

void scope:: addToEntList(entity a){
	entList.push_back(a);
}

void scope:: setOffset(int a){
	tempOffset=a;
}

void scope:: offsetPlusFour(){
	tempOffset+=4;
}

int scope:: getOffset(){
	return(tempOffset);
}

void scope:: setName(string a){
	name=a;
}

string scope:: getName(){
	return(name);
}

list<entity>scope:: getEntList(){
	return(entList);
}

void scope:: setEntList(list<entity> a){
	entList=a;
}

void scope:: addToEntity(entity a){
	entList.push_back(a);
}	

void scope:: setNestingLevel(int a){
	nestingLevel=a;
}

int scope:: getNestingLevel(){
	return(nestingLevel);
}

void lexical_analyzer();
void program();		void block(string name,int isItMain);		void optional_sign();	void do_while_stat();	void mul_oper();
void declarations();	void varlist();		void subprograms();	void func(entity ent);		void funcbody(string subName,entity ent,scope func);
void formalpars(entity ent,scope func);	void formalparlist(entity ent,scope func);	entity formalparitem(entity ent,scope func);	void sequence();	void brackets_seq();
void brack_or_stat();	void statement();	void assignment_stat();	void if_stat();		void elsepart();	
void exit_stat();	void return_stat();	void print_stat();	void while_stat();	void incase_stat();
void forcase_stat();	void call_stat();	void actualpars();	void actualparlist();	void actualparitem();
idList condition();	idList boolterm();	idList boolfactor();	string expression();	string term();
string factor();	string idtail(string fact);		void relational_oper();	void add_oper();	
void nextQuad();  	string newTemp(); 	string commandCounterToString();
		
list<int> makeList();
list<quadra> emptylist();
list<int> mergeList(list<int> list1,list<int> list2);
void backpatch(list<int> a,string z);

list<quadra> main_list;
list<scope> scope_list;

int main(int argc, char* argv[]){
	
	if(argc!= 2){
		printf("Give 1 file ending with .st\n");
		exit(1);
	}
	fp=fopen(argv[1],"r");
	if(fp==NULL){
		printf("file not found!\n");
		fclose(fp);
		exit(1);
	}

	while(!feof(fp)){
		lexical_analyzer();
		program();
	}
	fclose(fp);
	fw = fopen("2108_2023_test1.int","w");
	list<quadra>::iterator i;
	for(i=main_list.begin(); i!=main_list.end(); i++){
		int no=(*i).getid();
		string str1=(*i).getstring1();
		string str2=(*i).getstring2();
		string str3=(*i).getstring3();
		string str4=(*i).getstring4();
		std::string st1(str1);
		std::string st2(str2);
		std::string st3(str3);
		std::string st4(str4);
		fprintf(fw, "%d %s %s %s %s \n",no,st1.c_str(),st2.c_str(),st3.c_str(),st4.c_str());
	}
	fclose(fw);

	main_list.clear();
}

void lexical_analyzer(){
	int character;
	char ch[1];
	here1:
	token=NULL;
	token=(char*)malloc(30*sizeof(char));
	character=fgetc(fp);
	if(character == 32 || character==9 || character==10 || character==13){             //lefkoi xaraktires
		if(character==10)
			counter_line++;
		character=fgetc(fp);
		while(character == 32 || character==9 ||character==10 || character==13){
			if(character==10)
				counter_line++;
			character=fgetc(fp);
		}
	}
	if((character>=65 && character<=90) || (character>= 97 && character<=122)){
		sprintf(ch,"%c",character);	//kanoume ton ascii char
		strcat(token,ch);
		character=fgetc(fp);
		while((character>=65 && character<=90) || (character>= 97 && character<=122)||(character>=48 && character<=57) && strlen(token)!= 29){
			if(strlen(token)<30){
				sprintf(ch,"%c",character);	//kanoume ton ascii char
				strcat(token,ch);
			}
			character=fgetc(fp);
		}
		ungetc(character,fp);
		if(!strcmp(token,"forcase"))
			token_id=forcase_tk;
		else if(!strcmp(token,"and"))
			token_id=and_tk;
		else if(!strcmp(token,"declare"))
			token_id=declare_tk;
		else if(!strcmp(token,"do"))
			token_id=do_tk;
		else if(!strcmp(token,"else"))
			token_id=else_tk;
		else if(!strcmp(token,"enddeclare"))
			token_id=end_declare_tk;
		else if(!strcmp(token,"exit"))
			token_id=exit_tk;
		else if(!strcmp(token,"procedure"))
			token_id=procedure_tk;
		else if(!strcmp(token,"function"))
			token_id=function_tk;
		else if(!strcmp(token,"print"))
			token_id=print_tk;
		else if(!strcmp(token,"incase"))
			token_id=incase_tk;
		else if(!strcmp(token,"if"))
			token_id=if_tk;
		else if(!strcmp(token,"in"))
			token_id=in_tk;
		else if(!strcmp(token,"inout"))
			token_id=inout_tk;
		else if(!strcmp(token,"not"))
			token_id=not_tk;
		else if(!strcmp(token,"program"))
			token_id=program_tk;
		else if(!strcmp(token,"or"))
			token_id=or_tk;
		else if(!strcmp(token,"return"))
			token_id=return_tk;
		else if(!strcmp(token,"while"))
			token_id=while_tk;
		else if(!strcmp(token,"copy"))
			token_id=copy_tk;
		else if(!strcmp(token,"when"))
			token_id=when_tk;
		else if(!strcmp(token,"call"))
			token_id=call_tk;
		else
			token_id=variable_tk;
		return;
	}
	else if(character>=48 && character<=57){
		int num;
		sprintf(ch,"%c",character);	//kanoume ton ascii char
		strcat(token,ch);
		character=fgetc(fp);
		while(character>=48 && character<=57){
			sprintf(ch,"%c",character);	//kanoume ton ascii char
			strcat(token,ch);
			character=fgetc(fp);
		}
		num = atoi(token);
		if(num>32767){
			printf("ERROR:at line %d you gave the non acceptable number %d\n",counter_line,num);
		}
		token_id=num_tk;	
		ungetc(character,fp);	
	}
	else if(character==43){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=plus_tk;
	}
	else if(character==45){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=minus_tk;
	}
	else if(character==42){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=multiply_tk;
	}
	else if(character==47){        // maybe divide maybe comments
		character=fgetc(fp);
		if(character != 42){
			ungetc(character,fp);
			sprintf(ch,"%c",character);	
			//strcat(token,ch);
			strcat(token,"/");
			token_id=divide_tk;
		}
		else if(character==42){
			character=fgetc(fp);
			here:
			while(character!=42){				
				if(feof(fp)){
					printf("ERROR:the comments never ended and stream is finished\n");
				}
				if(character==10)
					counter_line++;  //allazoun grames ta sxolia
				character=fgetc(fp);
			}
			character=fgetc(fp);
			if(character==47){
				goto here1;
			}
			else{
				goto here;
			}		
		}
	}
	else if(character==60){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		character=fgetc(fp);
		if(character==61){
			sprintf(ch,"%c",character);	
			strcat(token,ch);
			token_id=smaller_or_equal_tk;
		}
		else if(character==62){
			sprintf(ch,"%c",character);	
			strcat(token,ch);
			token_id=not_equal_tk;
		}
		else{
			ungetc(character,fp);
			token_id=smaller_tk;
		}
	}
	else if(character==62){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		character=fgetc(fp);
		if(character==61){
			sprintf(ch,"%c",character);	
			strcat(token,ch);
			token_id=greater_or_equal_tk;
		}
		else{
			ungetc(character,fp);
			token_id=greater_tk;
		}
	}
	else if(character==58){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		character=fgetc(fp);
		if(character==61){
			sprintf(ch,"%c",character);	
			strcat(token,ch);
			token_id=assignment_tk;
		}
		else{
			printf("ERROR at line%d:after ':' expected '='\n",counter_line);
		}
	}
	else if(character==123){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=left_bracket_tk;
	}
	else if(character==125){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=right_bracket_tk;
	}
	else if(character==40){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=left_parenthesis_tk;
	}
	else if(character==41){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=right_parenthesis_tk;
	}
	else if(character==91){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=left_square_bracket_tk;
	}
	else if(character==93){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=right_square_bracket_tk;
	}
	else if(character==59){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=semicolon_tk;
	}
	else if(character==44){
		sprintf(ch,"%c",character);	
		strcat(token,ch);
		token_id=comma_tk;
	}
	else if(character==-1){
	}
	else{ 
		printf("ERROR:at line %d not acceptable symbol\n",counter_line);
			
	}				
			
}

void program(){

	if(token_id==program_tk){
		scope main;
		lexical_analyzer();
		if(token_id==variable_tk){
			string name=token;
			main.setName(token);
			main.setNestingLevel(0);
			nestingLevel++;
			main.setOffset(12);
			scope_list.push_front(main);
			lexical_analyzer();
			block(name,1);
		}
		else{
			printf("program  name expected at line %d\n",counter_line);
		}
	}
	else{
		//printf("the keyword program was expected at line %d\n",counter_line);
	}
}

void block(string name,int isItMain){
	int frameLength;
	
	if(token_id==left_bracket_tk){
		lexical_analyzer();
		declarations();
		subprograms();
		quadra blockName;
		nextQuad();
		blockName.genQuad("begin_block",name,"_","_");
		main_list.push_back(blockName);
		sequence();
		if(token_id==right_bracket_tk){
			lexical_analyzer();
		}
		else{
			printf("right bracket or semicolon was expected somewhere before line %d\n",counter_line);
		}
	}
	else{
		printf("left bracket expected\n");
	}
	if(isItMain==1){
		nextQuad();
		quadra halt;
		halt.genQuad("halt","_","_","_");
		main_list.push_back(halt);
	}
	quadra endBlock;
	nextQuad();
	endBlock.genQuad("end_block",name,"_","_");
	main_list.push_back(endBlock);

	
	list<argument> b;
	list<entity> a;
	list<scope>::iterator it;
	list<entity>::iterator iter;
	list<argument>::iterator itarg;
	it=scope_list.begin();

	cout<<"\nScope '"<<(*it).getName()  <<"' is ready and will be erased, pinakas simvolwn ews twra: \n \n";
	for(it=scope_list.begin(); it!= scope_list.end(); it++){
		cout << "scope: "<< (*it).getName() <<" nestingLevel: " <<(*it).getNestingLevel()<<endl ;
		a=(*it).getEntList();  
		for(iter=a.begin(); iter!=a.end(); iter++){
			cout <<"   "<< (*iter).getEntityType() <<":";
			cout <<	(*iter).getName() <<" ";
			if("parameter"==(*iter).getEntityType()){
				cout<<"Pass Type:" << (*iter).getParMode();			
			}
			if("func/proc"!=(*iter).getEntityType()){
				cout <<" offset: "<<(*iter).getOffset() << "\n";
			}
			if("func/proc"==(*iter).getEntityType()){
				b=(*iter).getArgList();
				cout<<" framelength: "<< (*iter).getFrameLength() ;
				cout<<"  pass types: ";
				for(itarg=b.begin(); itarg!=b.end(); itarg++){
					cout << (*itarg).getParMode()<< " ";
				}
				cout <<"  Start quad:" <<(*iter).getStartQuad() << endl;		
			}
		}
	}
	it=scope_list.begin();	
	scope_list.erase(it);
	nestingLevel--;
	
}


void declarations(){
	
	if(token_id==declare_tk){
		lexical_analyzer();
		varlist();
		if(token_id==end_declare_tk){
			lexical_analyzer();
		}
		else{
			printf("the keyword 'enddeclare' was expected at line %d\n",counter_line);
		}
	}
}

void varlist(){
	list<scope>::iterator it;
	entity ent;
	int tempOffset;
	scope k;		

	it=scope_list.begin();
	if(token_id==variable_tk){  //kouti entity
		ent.setEntityType("variable");
		ent.setName(token);
		tempOffset=(*it).getOffset();
		(*it).offsetPlusFour();
		ent.setOffset(tempOffset);
		(*it).addToEntList(ent);
		lexical_analyzer();
		while(token_id==comma_tk){
			lexical_analyzer();
			if(token_id==variable_tk){ 
				ent.setEntityType("variable");
				ent.setName(token);
				tempOffset=(*it).getOffset();
				(*it).offsetPlusFour();
				ent.setOffset(tempOffset);
				(*it).addToEntList(ent);
				lexical_analyzer(); 
			}
			else{
				printf("variable declaration expected after comma at line %d\n",counter_line);
			}
		}
	}
	
}

void subprograms(){
	entity ent;

	while(token_id==procedure_tk || token_id==function_tk){
		ent.setEntityType("func/proc");
		ent.setStartQuad(command_counter+10);
		if(token_id==procedure_tk)
			ent.setType("void");
		else
			ent.setType("int");
		func(ent);
	}
}

void func(entity ent){
	scope func;

	lexical_analyzer();
	string subName=token;	//entity name and new scope

	func.setName(subName);
	func.setNestingLevel(nestingLevel);
	nestingLevel++;
	func.setOffset(12);	
	ent.setName(subName);	
	if(token_id==variable_tk){
		lexical_analyzer();
		funcbody(subName,ent,func);
	}
	else{
		printf("name of function/procedure expected at line %d\n",counter_line);
	}

}

void funcbody(string subName,entity ent,scope func){
	formalpars(ent,func);
	block(subName,0);
}

void formalpars(entity ent,scope func){
	if(token_id==left_parenthesis_tk){
		lexical_analyzer();
		formalparlist(ent,func);
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
		}
		else{
			printf("right parenthesis was expected somewhere at line %d\n",counter_line);
		}
	}
}

void formalparlist(entity ent,scope func){
	list<scope>::iterator it;
	argument a;
	string passType;
	list<argument> arg;
	entity get;
	int tempOffset;
	int frameLength;
		
	it=scope_list.begin();
	get=formalparitem(ent,func);
	tempOffset=func.getOffset();
	func.offsetPlusFour();
	get.setOffset(tempOffset);
	func.addToEntList(get); 
	passType=get.getParMode();
	a.setParMode(passType);
	arg.push_back(a);
	while(token_id==comma_tk){
		lexical_analyzer();
		get=formalparitem(ent,func);
		tempOffset=func.getOffset();
		func.offsetPlusFour();
		get.setOffset(tempOffset);
		func.addToEntList(get); 
		passType=get.getParMode();
		a.setParMode(passType);
		arg.push_back(a);
	}
	ent.setArgList(arg);
	frameLength=func.getOffset();
	ent.setFrameLength(frameLength);
	(*it).addToEntList(ent);
	scope_list.push_front(func);
	
}

entity formalparitem(entity ent,scope func){
	entity entForScope;
	string passType,parName;

	if(token_id==in_tk || token_id==inout_tk || token_id==copy_tk){
		passType=token;
		lexical_analyzer();   //krataw in inout copy
		if(token_id==variable_tk){
			entForScope.setEntityType("parameter");
			entForScope.setName(token);
			entForScope.setParMode(passType);
			lexical_analyzer();   //krataw paramentro
		}
		else{
			printf("variable expected after keyword in/inout/copy at line %d\n",counter_line);
		}
	}
	else{
		printf("expected keyword in/inout/copy at line %d\n",counter_line);
	}
	return(entForScope);
}

void sequence(){
	statement();
	while(token_id==semicolon_tk){
		lexical_analyzer();
		statement();
	}
}

void brackets_seq(){
	if(token_id==left_bracket_tk){
		lexical_analyzer();
		sequence(); 
		if(token_id==right_bracket_tk){
			lexical_analyzer();
		}
		else{
			printf("right bracket expected at line %d\n",counter_line);
		}
	}
	else{
		printf("left bracket was expected at line %d\n",counter_line);
	}
}

void brack_or_stat(){
	if(token_id==assignment_tk || token_id==while_tk || token_id==if_tk || token_id==do_tk || token_id==exit_tk ||token_id==incase_tk || token_id==forcase_tk || token_id==call_tk || token_id==return_tk || token_id==print_tk)
		statement();
	else
		brackets_seq();
}

void statement(){
	if(token_id==variable_tk)
		assignment_stat(); // ready
	else if(token_id==if_tk)
		if_stat();      //ready
	else if(token_id==while_tk)
		while_stat();    //ready
	else if(token_id==do_tk)
		do_while_stat(); //ready
	else if(token_id==exit_tk)
		exit_stat();
	else if(token_id==incase_tk)
		incase_stat();   //ready
	else if(token_id==forcase_tk)
		forcase_stat();  //ready
	else if(token_id==call_tk)
		call_stat();     //ready
	else if(token_id==return_tk)
		return_stat();    //ready
	else if(token_id==print_tk)
		print_stat();    //ready
}
	
void assignment_stat(){
	string ePlace;
	string variableForAssign=token;
	quadra assign;
  
	lexical_analyzer();
	if(token_id==assignment_tk){
		lexical_analyzer();
		ePlace=expression();
		nextQuad();
		assign.genQuad(":=",ePlace,"_",variableForAssign);
		main_list.push_back(assign);
	}
	else{
		printf("assigment symbol ':=' expected!at line %d\n",counter_line);
	}
}

void if_stat(){
	idList B;
	list<int> ifList;
	quadra genif;

	lexical_analyzer();
	if(token_id==left_parenthesis_tk){
		lexical_analyzer();
		B=condition();
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
			nextQuad();
			backpatch(B.getTrueList(),commandCounterToString());
			command_counter-=10;
			brack_or_stat(); 
			nextQuad();
			ifList=makeList();
			ifList.push_back(command_counter);
			genif.genQuad("jump","_","_","_");
			main_list.push_back(genif);
			nextQuad();
			backpatch(B.getFalseList(),commandCounterToString());
			command_counter-=10;
			elsepart();
			nextQuad();
			backpatch(ifList,commandCounterToString());
			command_counter-=10;
		}
		else{
			printf("right parenthesis was expected at line %d\n",counter_line);
		}
	}
	else{
		printf("left parenthesis was expected at line %d\n",counter_line);
	}
}

void elsepart(){
	if(token_id==else_tk){
		lexical_analyzer();
		brack_or_stat();
	}
}

void do_while_stat(){
	string sQuad;
	idList c;
	
	nextQuad();
	sQuad=commandCounterToString();
	command_counter-=10;
	lexical_analyzer();
	brack_or_stat();
	if(token_id==while_tk){
		lexical_analyzer();
		if(token_id==left_parenthesis_tk){
			lexical_analyzer();
			c=condition();
			backpatch(c.getTrueList(),sQuad);
			nextQuad();
			backpatch(c.getFalseList(),commandCounterToString());
			command_counter-=10;
			if(token_id==right_parenthesis_tk){
				lexical_analyzer();
			}
			else{
				printf("right parenthesis was expected at line %d\n",counter_line);
			}
		}
		else{
			printf("left parenthesis was expected at line %d\n",counter_line);
		}
	}
	else{
		printf("the keyword 'while' was expected at line %d\n",counter_line);
	}
}

void exit_stat(){
	lexical_analyzer();
}

void return_stat(){
	string ePlace;
	quadra ret;
	
	lexical_analyzer();
	if(token_id==left_parenthesis_tk){
		lexical_analyzer();
		ePlace=expression();
		nextQuad();
		ret.genQuad("retv",ePlace,"_","_");
		main_list.push_back(ret);
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
		}
		else{
			printf("right parenthesis was expected at line %d\n",counter_line);
		}
	}
	else{
		printf("left parenthesis was expected at line %d\n",counter_line);
	}
}

void print_stat(){
	string ePlace;
	quadra genPrint;
	
	lexical_analyzer();
	if(token_id==left_parenthesis_tk){
		lexical_analyzer();
		ePlace=expression();
		nextQuad();
		genPrint.genQuad("out",ePlace,"_","_");
		main_list.push_back(genPrint);
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
		}
		else{
			printf("right parenthesis was expected at line %d\n",counter_line);
		}
	}
	else{
		printf("left parenthesis was expected at line %d\n",counter_line);
	}
}


void while_stat(){
	idList B;
	string Bquad;
	quadra genWhile;	
	
	lexical_analyzer();
	if(token_id==left_parenthesis_tk){
		nextQuad();
		Bquad=commandCounterToString();
		command_counter-=10;
		lexical_analyzer();
		B=condition();
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
			nextQuad();	
			backpatch(B.getTrueList(),commandCounterToString());
			command_counter-=10;
			brack_or_stat();
			nextQuad();
			genWhile.genQuad("jump","_","_",Bquad);
			main_list.push_back(genWhile);
			nextQuad();	
			backpatch(B.getFalseList(),commandCounterToString());
			command_counter-=10;
		}
		else{
			printf("right parenthesis was expected at line %d\n",counter_line);
		}
	}
	else{
		printf("left parenthesis was expected at line %d\n",counter_line);
	}
}

void forcase_stat(){
	idList c;
	string sQuad,stave;
	quadra genForcase;

		
	nextQuad();
	stave=commandCounterToString();
	command_counter-=10;
	lexical_analyzer();
	if(token_id==left_bracket_tk){
		lexical_analyzer();
		while(token_id==when_tk){
			lexical_analyzer();
			if(token_id==left_parenthesis_tk){
				lexical_analyzer();
				c=condition();
				nextQuad();
				sQuad=commandCounterToString();
				command_counter-=10;
				backpatch(c.getTrueList(),sQuad);
				if(token_id==right_parenthesis_tk){
					lexical_analyzer();
					brack_or_stat();		
				}
				else{
					printf("right parenth was expected at line %d\n",counter_line);
				}
				nextQuad();
				genForcase.genQuad("jump","_","_",stave);
				main_list.push_back(genForcase);
				nextQuad();
				sQuad=commandCounterToString();
				command_counter-=10;
				backpatch(c.getFalseList(),sQuad);
			}
			else{
				printf("left parenth was expected at line %d\n",counter_line);
			}			
		}
		if(token_id==right_bracket_tk){
			lexical_analyzer();
		}
		else{
			printf("right bracket was expected at line %d\n",counter_line);
		}
			
	}
	else{
		printf("left bracket was expected at line %d\n",counter_line);
	}
}

void incase_stat(){
	idList c;
	string sQuad,p0quad;
	quadra genFlag,genFlagAgain,checkFlag;


	nextQuad();
	p0quad=commandCounterToString();
	command_counter-=10;
	nextQuad();
	genFlag.genQuad(":=","1","_","flag_");
	main_list.push_back(genFlag);
	lexical_analyzer();
	if(token_id==left_bracket_tk){
		lexical_analyzer();
		while(token_id==when_tk){
			lexical_analyzer();
			if(token_id==left_parenthesis_tk){
				lexical_analyzer();
				c=condition();
				nextQuad();
				sQuad=commandCounterToString();
				command_counter-=10;
				backpatch(c.getTrueList(),sQuad);
				nextQuad();
				genFlagAgain.genQuad(":=","0","_","flag_");
				main_list.push_back(genFlagAgain);
				if(token_id==right_parenthesis_tk){
					lexical_analyzer();
					brack_or_stat();		
				}
				else{
					printf("right parenth was expected at line %d\n",counter_line);
				}
				nextQuad();
				sQuad=commandCounterToString();
				command_counter-=10;
				backpatch(c.getFalseList(),sQuad);
			}
			else{
				printf("left parenth was expected at line %d\n",counter_line);
			}			
		}
		nextQuad();
		checkFlag.genQuad("=","0","flag_",p0quad);
		main_list.push_back(checkFlag);
		if(token_id==right_bracket_tk){
			lexical_analyzer();
		}
		else{
			printf("right bracket was expected at line %d\n",counter_line);
		}
			
	}
	else{
		printf("left bracket was expected at line %d\n",counter_line);
	}
}

void call_stat(){
	string procName;
	quadra genCall;
	
	lexical_analyzer();
	procName=token;
	if(token_id==variable_tk){
			lexical_analyzer();
			actualpars();
			nextQuad();
			genCall.genQuad("call",procName,"_","_");
			main_list.push_back(genCall);						
	}
	else{
		printf("name of function/procedure after call was expected at line %d\n",counter_line);
	}
}

void actualpars(){
	if(token_id==left_parenthesis_tk){
		lexical_analyzer();
		actualparlist();
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
		}
		else{
			printf("right parenthesis was expected at line %d\n",counter_line);
		}
	}
}

void actualparlist(){
	actualparitem();
	while(token_id==comma_tk){
		lexical_analyzer();
		actualparitem();
	}
}

void actualparitem(){
	quadra genPar;
	string e;
	
	if(token_id==in_tk){
		lexical_analyzer();
		e=expression();
		nextQuad();
		genPar.genQuad("par",e,"CV","_");
		main_list.push_back(genPar);
	}
	else if(token_id==inout_tk){
		lexical_analyzer();
		if(token_id==variable_tk){
			nextQuad();
			genPar.genQuad("par",token,"REF","_");
			main_list.push_back(genPar);
			lexical_analyzer();
		}
		else{
			printf("variable expected after inout at line %d\n",counter_line);
		}
	}
	else if(token_id==copy_tk){
		lexical_analyzer();
		if(token_id==variable_tk){
			nextQuad();
			genPar.genQuad("par",token,"CP","_");
			main_list.push_back(genPar);
			lexical_analyzer();
		}
		else{
			printf("variable expected after copy at line %d\n",counter_line);
		}
	}
	else{
		printf("'in' or 'inout' or 'copy' keyword expected at line %d\n",counter_line);
	}
}

idList condition(){
	idList B,Q1,Q2;
	Q1=boolterm(); //KANONIKA GIRIZEI TO Q1 I BOOLTERM
	B.setTrue(Q1.getTrueList());
	B.setFalse(Q1.getFalseList());
	while(token_id==or_tk){
		nextQuad();	
		backpatch(B.getFalseList(),commandCounterToString());
		command_counter-=10;
		lexical_analyzer();
		Q2=boolterm(); //KANONIKA GIRIZEI TO Q2 I BOOLTERM
		B.setTrue(mergeList(B.getTrueList(),Q2.getTrueList()));
		B.setFalse(Q2.getFalseList());
	}
	return(B);
}

idList boolterm(){
	idList Q,R1,R2;
	R1=boolfactor();
	Q.setTrue(R1.getTrueList());
	Q.setFalse(R1.getFalseList());
	while(token_id==and_tk){
		nextQuad();	
		backpatch(Q.getTrueList(),commandCounterToString());
		command_counter-=10;
		lexical_analyzer();
		R2=boolfactor();
		Q.setFalse(mergeList(Q.getFalseList(),R2.getFalseList()));
		Q.setTrue(R2.getTrueList());
	}
	return(Q);
}

idList boolfactor(){
	quadra relationalQuadra,jumpQuadra;
	idList R,B;
	string ePlace1,ePlace2;
	string whatReOper;

	if(token_id==not_tk){
		lexical_analyzer();
		if(token_id==left_square_bracket_tk){
			lexical_analyzer();
			B=condition();
			if(token_id==right_square_bracket_tk){
				lexical_analyzer();
			}
			else{
				printf("right square bracket was expected at line %d\n",counter_line);
			}
		}
		else{
			printf("left square bracket was expected at line %d\n",counter_line);
		}
	}
	else if(token_id==left_square_bracket_tk){
		lexical_analyzer();
		B=condition();
		if(token_id==right_square_bracket_tk){
			lexical_analyzer();
		}
		else{
			printf("right square bracket was expected at line %d\n",counter_line);
		}
	}
	else{
		ePlace1=expression();
		whatReOper=token;
		relational_oper();
		ePlace2=expression();
		nextQuad();
		R.addToTrue(command_counter);
		relationalQuadra.genQuad(whatReOper,ePlace1,ePlace2,"_");
		main_list.push_back(relationalQuadra);
		nextQuad();
		R.addToFalse(command_counter);
		jumpQuadra.genQuad("jump","_","_","_");
		main_list.push_back(jumpQuadra);
	}
	return(R);
}

string expression(){
	list<scope>::iterator it;
	entity ent;
	int tempOffset;
	scope k;		
	string ePlace,t1Place,t2Place,w;
	quadra plusOrMinus;

	it=scope_list.begin();
	optional_sign();
	t1Place=term();
	while(token_id==plus_tk || token_id==minus_tk){
		if(token_id==plus_tk){
			add_oper();
			t2Place=term(); 
			w=newTemp();
			ent.setEntityType("temp_variable");
			ent.setName(w);
			tempOffset=(*it).getOffset();
			(*it).offsetPlusFour();
			ent.setOffset(tempOffset); 
			(*it).addToEntList(ent);
			nextQuad();
			plusOrMinus.genQuad("+",t1Place,t2Place,w);
			t1Place=w;
			main_list.push_back(plusOrMinus);
		}
		else if(token_id==minus_tk){
			add_oper();
			t2Place=term(); 
			w=newTemp();
			ent.setEntityType("temp_variable");
			ent.setName(w);
			tempOffset=(*it).getOffset();
			(*it).offsetPlusFour();
			ent.setOffset(tempOffset);
			(*it).addToEntList(ent); 
			nextQuad();
			plusOrMinus.genQuad("-",t1Place,t2Place,w);
			t1Place=w;
			main_list.push_back(plusOrMinus);
		}
			
		
	}
	ePlace=t1Place;
	return(ePlace);
}

string term(){
	list<scope>::iterator it;
	entity ent;
	int tempOffset;
	scope k; 
	string tPlace,f1Place,f2Place,w;
	quadra multOrDiv;
	
	it=scope_list.begin();
	f1Place=factor();
	while(token_id==multiply_tk || token_id==divide_tk){
		if(token_id==multiply_tk){
			mul_oper();
			f2Place=factor();
			w=newTemp();
			ent.setEntityType("temp_variable");
			ent.setName(w);
			tempOffset=(*it).getOffset();
			(*it).offsetPlusFour();
			ent.setOffset(tempOffset);
			(*it).addToEntList(ent); 
			nextQuad();
			multOrDiv.genQuad("*",f1Place,f2Place,w);
			f1Place=w;
			main_list.push_back(multOrDiv);
		}
		else if(token_id==divide_tk){
			mul_oper();
			f2Place=factor();
			w=newTemp();
			ent.setEntityType("temp_variable");
			ent.setName(w);
			tempOffset=(*it).getOffset();
			(*it).offsetPlusFour();
			ent.setOffset(tempOffset);
			(*it).addToEntList(ent); 
			nextQuad();
			multOrDiv.genQuad("/",f1Place,f2Place,w);
			f1Place=w;
			main_list.push_back(multOrDiv);
		}
			
	}
	tPlace=f1Place;
	return(tPlace);
}

string factor(){
	string fact;
	if(token_id==num_tk){
		fact=token;
		lexical_analyzer();
	}
	else if(token_id==left_parenthesis_tk){
		lexical_analyzer();
		fact=expression();
		if(token_id==right_parenthesis_tk){
			lexical_analyzer();
		}
		else{
			printf("right parenthesis was expected at line %d\n",counter_line);
		}
	}
	else if(token_id==variable_tk){
		fact=token;
		lexical_analyzer();
		fact=idtail(fact);
	}
	else{
		printf("constant num or variable or left parenthesis was expected at line %d\n",counter_line);
	}
	return(fact);
}

string idtail(string fact){
	quadra genRet,genCall;
	string w=fact;
	entity ent;
	list<scope>::iterator it;
	int tempOffset;
		
	it=scope_list.begin();
	if(token_id==left_parenthesis_tk){
	// EDW PERA MOLIS MPW SIMENEI OTI EXW SINARTISI
		actualpars();
		w=newTemp();
		ent.setEntityType("temp_variable");
		ent.setName(w);
		tempOffset=(*it).getOffset();
		(*it).offsetPlusFour();
		ent.setOffset(tempOffset); 
		(*it).addToEntList(ent);
		nextQuad();
		genRet.genQuad("par",w,"RET","_");
		main_list.push_back(genRet);
		nextQuad();
		genCall.genQuad("call",fact,"_","_");
		main_list.push_back(genCall);
	}
	return(w);
}

void relational_oper(){
	if(token_id==equal_tk)
		lexical_analyzer();
	else if(token_id==smaller_tk)
		lexical_analyzer();
	else if(token_id==greater_tk)
		lexical_analyzer();
	else if(token_id==greater_or_equal_tk)
		lexical_analyzer();
	else if(token_id==smaller_or_equal_tk)
		lexical_analyzer();
	else if(token_id==not_equal_tk)
		lexical_analyzer();
	else{
		printf("relational operator was expected at line %d\n",counter_line);
	}
}

void add_oper(){
	if(token_id==plus_tk)
		lexical_analyzer();
	else if(token_id==minus_tk)
		lexical_analyzer();
	else{
		printf("add operator was expected at line %d\n",counter_line);
	}
}

void mul_oper(){
	if(token_id==multiply_tk)
		lexical_analyzer();
	else if(token_id==divide_tk)
		lexical_analyzer();
	else{
		printf("mul operator was expected at line %d\n",counter_line);
	}
}

void optional_sign(){
	if(token_id==plus_tk || token_id==minus_tk){
		add_oper();
	}
}
	
void nextQuad(){
	command_counter+=10;
	//return(command_counter);
}

string newTemp(){
	temp_counter++;
	string temp;
	char Result[1];

	sprintf(Result,"%d",temp_counter);
	temp = "T_" + (string) Result;
	return(temp);
}

string commandCounterToString(){
	string temp;
	char Result[1];

	sprintf(Result,"%d",command_counter);
	temp = (string) Result;
	return(temp);
}

list<quadra> emptylist(){
	list<quadra> temp;
	return(temp);
}

list<int> makeList(){
	list<int> a;
	return(a);
}

list<int> mergeList(list<int> list1,list<int> list2){
	list1.merge(list2);
	return(list2);
}

void backpatch(list<int> a,string z){
	list<int>::iterator iter;
	list<quadra>::iterator it;
		for(iter=a.begin(); iter!=a.end(); iter++){
			for(it=main_list.begin(); it!=main_list.end(); it++){
				if((*iter)==(*it).getid()){
					(*it).set_s4(z);
				}
			}
		}
}
		



