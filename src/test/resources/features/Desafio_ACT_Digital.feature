#language: pt
#encoding: UTF-8
@automationtesting
Funcionalidade: Automacao das funcionalidades: “Register”, “Switch > Frames”, “Widgets > Datapicker”, “Widgets > Slider”.

  @Register
  Cenario: Registro de usuario
    Dado que eu esteja na URL "Register.html"
    E que eu preencha os campos do cadastro via "input" utilizando a "Full Name*", "Vinicius" e "1"
    E que eu preencha os campos do cadastro via "input" utilizando a "Full Name*", "Moreira" e "2"
    E que eu preencha os campos do cadastro via "textarea" utilizando a "Address", "Girassol - GO" e "1"
    E que eu preencha os campos do cadastro via "input" utilizando a "Email address*", "automation@mailinator.com" e "1"
    E que eu preencha os campos do cadastro via "input" utilizando a "Phone*", "6199290154" e "1"
    E que eu clique no campo via "Gender*" e clique no botao "Male"
    E que eu clique no campo via "Hobbies" e clique no botao "Cricket"
    E que eu clique no campo via "Hobbies" e clique no botao "Movies"
    E que eu clique no campo via "Hobbies" e clique no botao "Hockey"
    E que eu selecione no campo via "Skills" e "APIs" e "1"
    E que eu selecione no campo via "Country*" e "Australia" e "1"
    E que eu preencha os campos select country "Thai" e languages
    E que eu preencha os campos do cadastro Select Country com "Japan"
    E que eu selecione no campo via "Date Of Birth" e "1999" e "1"
    E que eu selecione no campo via "Date Of Birth" e "April" e "2"
    E que eu selecione no campo via "Date Of Birth" e "17" e "3"
    E que eu preencha os campos do cadastro via "input" utilizando a "Password", "Vinicius#123" e "1"
    E que eu preencha os campos do cadastro via "input" utilizando a "Confirm Password", "Vinicius#123" e "1"
    Entao pressiono o botao de salvar mas nada ocorre por que a api esta off rsrs

  @Frames
  Cenario: Preenchimento de iframe 1
    Dado que eu esteja na URL "Frames.html"
    Quando eu preencher o iframe "Já acabou jéssica?"

  @Frames
  Cenario: Preenchimento de iframe 2
    Dado que eu esteja na URL "Frames.html"
    Quando eu preencher o iframe dentro do iframe "Fácil assim ?"