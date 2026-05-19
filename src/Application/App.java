package Application;

import Objects.Livro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    Repositorio r = new Repositorio();

    @Override
    public void start(Stage primaryStage) {
        VBox containerPrincipal = new VBox(30);
        HBox hbOpcoes = new HBox(20);
        containerPrincipal.getChildren().addAll(hbOpcoes);

        Button btnAdicionar = new Button("Adicionar Livro");
        Button btnRemover = new Button("Remover Livro");
        Button btnAtualizar = new Button("Atualizar");
//        Button btnBuscar = new Button("Buscar");

        hbOpcoes.getChildren().addAll(btnAdicionar, btnRemover, btnAtualizar);


        btnAdicionar.setOnAction(e -> {
            containerPrincipal.getChildren().clear();
            VBox containerAdd = new VBox(20);
            HBox hbNomeLivro = new HBox(10);
            Label lblLivro = new Label("Insira o titulo do Livro: ");
            TextField fdNomeLivro = new TextField();
            hbNomeLivro.getChildren().addAll(lblLivro, fdNomeLivro);

            HBox hbAutor = new HBox(10);
            Label lblAutor = new Label("Insira o nome do Autor: ");
            TextField fdAutor = new TextField();
            hbAutor.getChildren().addAll(lblAutor, fdAutor);

            CheckBox checkLido = new CheckBox("Lido");
            Button btnAdd = new Button("Adicionar");
            Button btnVoltarAdd = new Button("Voltar");
            HBox hbErro = new HBox();
            HBox hbAcoes = new HBox(10);
            hbAcoes.getChildren().addAll(btnAdd, btnVoltarAdd);

            containerAdd.getChildren().addAll(hbNomeLivro, hbAutor, checkLido, hbAcoes, hbErro);
            containerPrincipal.getChildren().addAll(containerAdd);

            btnAdd.setOnAction(a -> {
                if (fdNomeLivro.getText().isBlank() || fdAutor.getText().isBlank()) {
                    hbErro.getChildren().clear();
                    hbErro.getChildren().add(new Label("Ops! Preencha todos os campos."));
                } else {
                    Livro novoLivro = new Livro(fdNomeLivro.getText(), fdAutor.getText(), checkLido.isSelected());
                    fdNomeLivro.setText("");
                    fdAutor.setText("");
                    checkLido.setSelected(false);
                    r.create(novoLivro);
                    r.read();
                }
            });

            btnVoltarAdd.setOnAction(a -> {
                containerPrincipal.getChildren().clear();
                containerPrincipal.getChildren().addAll(hbOpcoes);
            });
        });

        btnRemover.setOnAction(e -> {
            containerPrincipal.getChildren().clear();

            VBox containerRemover = new VBox(20);

            HBox hbBTNRemov = new HBox(20);
            Button btnRemovId = new Button("Id");
            Button btnRemovTitulo = new Button("Titulo");
            hbBTNRemov.getChildren().addAll(btnRemovId, btnRemovTitulo);

            HBox hbInputId = new HBox(10);
            Label lblId = new Label("Digite o ID: ");
            TextField fieldId = new TextField();
            hbInputId.getChildren().addAll(lblId, fieldId);

            HBox hbInputTitulo = new HBox(10);
            Label lblTitulo = new Label("Digite o titulo: ");
            TextField fieldTitulo = new TextField();
            hbInputTitulo.getChildren().addAll(lblTitulo, fieldTitulo);

            final String[] modo = {""};
            final HBox hbCampoAtivo = new HBox();

            Button btnRemoverLivro = new Button("Remover");
            Button btnVoltarRemov = new Button("Voltar");
            HBox hbAcoes = new HBox(10);
            hbAcoes.getChildren().addAll(btnRemoverLivro, btnVoltarRemov);

            HBox hbErroRemov = new HBox();

            containerRemover.getChildren().addAll(
                hbBTNRemov,
                hbCampoAtivo,
                hbAcoes,
                hbErroRemov
            );
            containerPrincipal.getChildren().addAll(containerRemover);

            btnRemovId.setOnAction(a -> {
                modo[0] = "id";
                hbCampoAtivo.getChildren().setAll(hbInputId);
                hbErroRemov.getChildren().clear();
                fieldId.clear();
            });

            btnRemovTitulo.setOnAction(a -> {
                modo[0] = "titulo";
                hbCampoAtivo.getChildren().setAll(hbInputTitulo);
                hbErroRemov.getChildren().clear();
                fieldTitulo.clear();
            });

            btnRemoverLivro.setOnAction(remov -> {
                hbErroRemov.getChildren().clear();

                if (modo[0].equals("id")) {
                    try {
                        int id = Integer.parseInt(fieldId.getText());
                        r.delete(id);
                        r.read();
                        containerPrincipal.getChildren().clear();
                        containerPrincipal.getChildren().addAll(hbOpcoes);
                    } catch (NumberFormatException ex) {
                        hbErroRemov.getChildren().add(new Label("ID inválido! Digite apenas números."));
                    }

                } else if (modo[0].equals("titulo")) {
                    if (fieldTitulo.getText().isBlank()) {
                        hbErroRemov.getChildren().add(new Label("Digite um título válido."));
                    } else {
                        r.delete(fieldTitulo.getText());
                        r.read();
                        containerPrincipal.getChildren().clear();
                        containerPrincipal.getChildren().addAll(hbOpcoes);
                    }

                } else {
                    hbErroRemov.getChildren().add(new Label("Selecione ID ou Título primeiro."));
                }
            });

            btnVoltarRemov.setOnAction(a -> {
                containerPrincipal.getChildren().clear();
                containerPrincipal.getChildren().addAll(hbOpcoes);
            });
        });
        btnAtualizar.setOnAction(e ->{
        	containerPrincipal.getChildren().clear();
        	VBox containerUpdate = new VBox(20);
        	
        	HBox hbId = new HBox(10);
        	Label lblId = new Label("Insira o Id da obra: ");
        	TextField fieldId = new TextField();
        	hbId.getChildren().addAll(lblId, fieldId);
        	
        	HBox hbTitulo = new HBox(10);
        	Label lblTitulo = new Label("Insira o titulo atualizado da obra: ");
        	TextField fieldTitulo = new TextField();
        	hbTitulo.getChildren().addAll(lblTitulo, fieldTitulo);
        	
        	
        	HBox hbAutor = new HBox(10);
        	Label lblAutor = new Label("Insira o autor atualizado da obra: ");
        	TextField fieldAutor = new TextField();
        	hbAutor.getChildren().addAll(lblAutor, fieldAutor);
        	
        	HBox hbLido = new HBox(10);
        	CheckBox lido = new CheckBox("Lido");
        	Button btnUpdate = new Button("Atualizar");
        	hbLido.getChildren().addAll(lido);
        	Button btnVoltar = new Button("Voltar");
        	HBox hbErro = new HBox();
        	//Container de botoes
        	HBox hbAcoes = new HBox(10);
            hbAcoes.getChildren().addAll(btnUpdate, btnVoltar);
        	
        	containerUpdate.getChildren().addAll(hbId, hbTitulo ,hbAutor, hbLido, hbErro, hbAcoes);
        	containerPrincipal.getChildren().addAll(containerUpdate);
        	
        	btnUpdate.setOnAction(a -> {
        		if(fieldId.getText().isBlank()||fieldTitulo.getText().isBlank() || fieldAutor.getText().isBlank()) {
        			hbErro.getChildren().add(new Label("Ops! Preencha todos os campos."));
        		}else {
        			hbErro.getChildren().clear();
        			r.update(Integer.parseInt(fieldId.getText()), fieldAutor.getText(), fieldTitulo.getText(), lido.isSelected());
            		fieldId.setText("");
            		fieldAutor.setText("");
            		fieldTitulo.setText("");
            		lido.setSelected(false);
        		}
        		
        	});
        	btnVoltar.setOnAction(a -> {
                containerPrincipal.getChildren().clear();
                containerPrincipal.getChildren().addAll(hbOpcoes);
            });
        });
        
//        REFATORAR CODIGO DO REPOSITORIO ANTES DE EXIBIR LIVROS NA TELA
//        Label lbl = new Label(r.read());
//        containerPrincipal.getChildren().add(lbl);
        Scene scene = new Scene(containerPrincipal, 400, 300);
        primaryStage.setTitle("Minha Biblioteca");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    


    public static void main(String[] args) {
        launch(args);
    }
}