package com.tmb.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe responsável por gerenciar a conexão com o banco SQLite.
 * Implementa o padrão Singleton para garantir uma única instância.
 */
public class SQLiteConnection implements DatabaseConnection {

    private static final Logger logger = LogManager.getLogger(SQLiteConnection.class);
    private static final String URL = "jdbc:sqlite:databases/TOPSPEED.db"; // ⚠️ extensão minúscula por convenção
    private static SQLiteConnection instance; // correção de nome
    private Connection connection;

    private SQLiteConnection() {
        try {
            // Carrega o driver do SQLite (opcional nas versões recentes, mas mantido por compatibilidade)
            Class.forName("org.sqlite.JDBC");
            this.connection = createConnection();
        } catch (ClassNotFoundException e) {
            logger.error("Driver JDBC do SQLite não encontrado.", e);
        }
    }

    /**
     * Cria uma nova conexão com o banco SQLite.
     * Este método é chamado internamente para evitar duplicação de código.
     */
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            logger.error("Erro ao criar conexão com o SQLite.", e);
            return null;
        }
    }

    @Override
    public Connection getConnection() {
        try {
            // Garante que a conexão está ativa; se estiver fechada, reabre.
            if (connection == null || connection.isClosed()) {
                connection = createConnection();
            }
        } catch (SQLException e) {
            logger.error("Erro ao verificar o estado da conexão SQLite.", e);
        }
        return connection;
    }

    /**
     * Retorna a instância única da conexão (Singleton).
     */
    public static synchronized SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }

    /**
     * Fecha a conexão atual, se existir.
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Conexão SQLite encerrada com sucesso.");
            } catch (SQLException e) {
                logger.error("Erro ao fechar a conexão SQLite.", e);
            }
        }
    }
}
