package application;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Livro;
import repository.Repositorio;

public class App extends Application {
    private Repositorio repositorio = new Repositorio();
    private VBox root;
    String cssPath = getClass().getResource("/view/style.css").toExternalForm();

    @Override
    public void start(Stage primaryStage) {
        root = new VBox(30);
        root.getChildren().add(telaMenu());
        root.getStylesheets().add(cssPath);
        
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("EstantX Virtual ");
        primaryStage.setMaximized(true);
        primaryStage.show();
       
    }

//    serve para navegar entre telas
    private void navegar(Node tela) {
        root.getChildren().setAll(tela);
    }

//    cria campos

    private HBox campoForm(String rotulo, TextField campo) {
        HBox hb = new HBox(10);
        hb.getChildren().addAll(new Label(rotulo), campo);
        return hb;
    }

//    Telas

    private Node telaMenu() {
        VBox container = new VBox(20);
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        Label titulo = new Label("EstantX Virtual");
        titulo.getStyleClass().add("h1");
        Button btnAdicionar = new Button("Adicionar Livro");
        btnAdicionar.getStyleClass().add("btnPrincipais");
        Button btnRemover   = new Button("Remover Livro");
        btnRemover.getStyleClass().add("btnPrincipais");
        Button btnAtualizar = new Button("Atualizar");
        btnAtualizar.getStyleClass().add("btnPrincipais");
        Button btnBuscar    = new Button("Buscar");
        btnBuscar.getStyleClass().add("btnPrincipais");
   
        HBox hbAcoes = new HBox(10);
        hbAcoes.setAlignment(Pos.CENTER);
        
        hbAcoes.getChildren().addAll(btnAdicionar, btnRemover, btnAtualizar, btnBuscar);
        header.getChildren().addAll(titulo,hbAcoes);
        header.getStyleClass().add("header");
        VBox containerReadLivros = new VBox();
        Label txtH2 = new Label("Livros Registrados");
        txtH2.getStyleClass().add("h2");
        TextArea txtLivros = new TextArea(repositorio.read());
        txtLivros.setEditable(false);
        txtLivros.setWrapText(true);
        txtLivros.getStyleClass().add("p");
        containerReadLivros.getChildren().addAll(txtH2, txtLivros);
        container.getChildren().addAll(header, containerReadLivros );
        btnAdicionar.setOnAction(e -> navegar(telaAdicionar()));
        btnRemover  .setOnAction(e -> navegar(telaRemover()));
        btnAtualizar.setOnAction(e -> navegar(telaAtualizar()));
        btnBuscar.setOnAction(e->navegar(telaBuscar()));
        return container;
    }

    private Node telaAdicionar() {
        VBox container = new VBox(20);
        TextField fdTitulo = new TextField();
        fdTitulo.getStyleClass().add("text-field");
        TextField fdAutor  = new TextField();
        fdAutor.getStyleClass().add("text-field");
        CheckBox  checkLido = new CheckBox("Lido");
        Label     lblErro   = new Label();
        Button    btnAdd    = new Button("Adicionar");
        Button    btnVoltar = new Button("Voltar");

        HBox hbAcoes = new HBox(10);
        hbAcoes.getChildren().addAll(btnAdd, btnVoltar);

        container.getChildren().addAll(
            campoForm("Título: ", fdTitulo),
            campoForm("Autor: ",  fdAutor),
            checkLido,
            hbAcoes,
            lblErro
        );

        btnAdd.setOnAction(e -> {
            if (fdTitulo.getText().isBlank() || fdAutor.getText().isBlank()) {
                lblErro.setText("Ops! Preencha todos os campos.");
            } else {
                repositorio.create(new Livro(fdTitulo.getText(), fdAutor.getText(), checkLido.isSelected()));
                fdTitulo.clear();
                fdAutor.clear();
                checkLido.setSelected(false);
                lblErro.setText("");
            }
        });

        btnVoltar.setOnAction(e -> navegar(telaMenu()));
        btnVoltar.getStyleClass().add("btnPrincipais");
        return container;
    }

    private Node telaRemover() {
        VBox container = new VBox(20);

        TextField fdId     = new TextField();
        fdId.getStyleClass().add("text-field");
        TextField fdTitulo = new TextField();
        fdTitulo.getStyleClass().add("text-field");
        Label     lblErro     = new Label();
        HBox      hbCampoAtivo = new HBox();
        final String[] modo   = {""};

        Button btnPorId     = new Button("Por ID");
        Button btnPorTitulo = new Button("Por Título");
        Button btnRemover   = new Button("Remover");
        Button btnVoltar    = new Button("Voltar");

        HBox hbModo  = new HBox(10);
        HBox hbAcoes = new HBox(10);
        hbModo .getChildren().addAll(btnPorId, btnPorTitulo);
        hbAcoes.getChildren().addAll(btnRemover, btnVoltar);

        container.getChildren().addAll(hbModo, hbCampoAtivo, hbAcoes, lblErro);

        btnPorId.setOnAction(e -> {
            modo[0] = "id";
            hbCampoAtivo.getChildren().setAll(campoForm("ID: ", fdId));
            fdId.clear();
            lblErro.setText("");
        });

        btnPorTitulo.setOnAction(e -> {
            modo[0] = "titulo";
            hbCampoAtivo.getChildren().setAll(campoForm("Título: ", fdTitulo));
            fdTitulo.clear();
            lblErro.setText("");
        });

        btnRemover.setOnAction(e -> {
            lblErro.setText("");
            switch (modo[0]) {
                case "id" -> {
                    try {
                        repositorio.delete(Integer.parseInt(fdId.getText()));
                        navegar(telaMenu());
                    } catch (NumberFormatException ex) {
                        lblErro.setText("ID inválido! Digite apenas números.");
                    }
                }
                case "titulo" -> {
                    if (fdTitulo.getText().isBlank()) {
                        lblErro.setText("Digite um título válido.");
                    } else {
                        repositorio.delete(fdTitulo.getText());
                        navegar(telaMenu());
                    }
                }
                default -> lblErro.setText("Selecione ID ou Título primeiro.");
            }
        });

        btnVoltar.setOnAction(e -> navegar(telaMenu()));
        btnVoltar.getStyleClass().add("btnPrincipais");
        return container;
    }

    private Node telaAtualizar() {
        VBox container = new VBox(20);

        TextField fdId     = new TextField();
        fdId.getStyleClass().add("text-field");
        TextField fdTitulo = new TextField();
        fdTitulo.getStyleClass().add("text-field");
        TextField fdAutor  = new TextField();
        fdAutor.getStyleClass().add("text-field");
        CheckBox  lido        = new CheckBox("Lido");
        Label     lblErro     = new Label();
        Button    btnUpdate   = new Button("Atualizar");
        Button    btnVoltar   = new Button("Voltar");

        HBox hbAcoes = new HBox(10);
        hbAcoes.getChildren().addAll(btnUpdate, btnVoltar);

        container.getChildren().addAll(
            campoForm("ID: ",     fdId),
            campoForm("Título: ", fdTitulo),
            campoForm("Autor: ",  fdAutor),
            lido,
            lblErro,
            hbAcoes
        );

        btnUpdate.setOnAction(e -> {
            if (fdId.getText().isBlank() || fdTitulo.getText().isBlank() || fdAutor.getText().isBlank()) {
                lblErro.setText("Ops! Preencha todos os campos.");
            } else {
                lblErro.setText("");
                repositorio.update(Integer.parseInt(fdId.getText()), fdAutor.getText(), fdTitulo.getText(), lido.isSelected());
                fdId.clear();
                fdTitulo.clear();
                fdAutor.clear();
                lido.setSelected(false);
            }
        });

        btnVoltar.setOnAction(e -> navegar(telaMenu()));
        btnVoltar.getStyleClass().add("btnPrincipais");
        return container;
    }
    private Node telaBuscar() {
        VBox container = new VBox(20);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbTitulo = new RadioButton("Título");
        RadioButton rbAutor  = new RadioButton("Autor");
        RadioButton rbId     = new RadioButton("ID");
        rbTitulo.setToggleGroup(group);
        rbAutor .setToggleGroup(group);
        rbId    .setToggleGroup(group);
        rbTitulo.setSelected(true);

        HBox hbRadios = new HBox(10);
        hbRadios.getChildren().addAll(rbTitulo, rbAutor, rbId);
        
        TextField fdBusca = new TextField();
        fdBusca.getStyleClass().add("text-field");
        fdBusca.getStyleClass().add("text-field");


        HBox hbCampo = new HBox(10);
        hbCampo.getChildren().setAll(new Label("Título:"), fdBusca);

        Label lblErro      = new Label();
        Label lblResultado = new Label();
        Button btnProcurar = new Button("Procurar");
        Button btnVoltar   = new Button("Voltar");

        HBox hbAcoes = new HBox(10);
        hbAcoes.getChildren().addAll(btnProcurar, btnVoltar);
        

        container.getChildren().addAll( hbCampo, hbRadios, hbAcoes, lblErro,lblResultado);

        // Atualiza o label do campo quando o radio muda
        group.selectedToggleProperty().addListener((obs,anterior,atual) -> {
            fdBusca.clear();
            lblErro.setText("");
            lblResultado.setText("");
            if (atual == rbTitulo) hbCampo.getChildren().setAll(new Label("Título:"), fdBusca);
            if (atual == rbAutor)  hbCampo.getChildren().setAll(new Label("Autor:"),  fdBusca);
            if (atual == rbId)     hbCampo.getChildren().setAll(new Label("ID:"),     fdBusca);
        });

        btnProcurar.setOnAction(e -> {
            lblErro.setText("");
            lblResultado.setText("");

            if (fdBusca.getText().isBlank()) {
                lblErro.setText("Preencha o campo de busca.");
                return;
            }

            if (rbTitulo.isSelected()) {
               
                lblResultado.setText(repositorio.buscarPorTitulo(fdBusca.getText()));

            } else if (rbAutor.isSelected()) {
                lblResultado.setText(repositorio.buscarPorAutor(fdBusca.getText()));

            } else if (rbId.isSelected()) {
                try {
                    lblResultado.setText(repositorio.buscarPorId(Integer.parseInt(fdBusca.getText())));
                } catch (NumberFormatException ex) {
                    lblErro.setText("ID inválido! Digite apenas números.");
                }
            }
        });

        btnVoltar.setOnAction(e -> navegar(telaMenu()));
        btnVoltar.getStyleClass().add("btnPrincipais");
        return container;
    }

    public static void main(String[] args) {
        launch(args);
    }
}