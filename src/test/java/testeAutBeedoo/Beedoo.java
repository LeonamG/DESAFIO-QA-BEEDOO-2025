package testeAutBeedoo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class Beedoo {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver",
                "C:\\Users\\leona\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized",
                "--allow-insecure-localhost",
                "--allow-running-insecure-content",
                "--ignore-certificate-errors");

        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://creative-sherbet-a51eac.netlify.app/");
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    // ==========================================
    // 🔹 Método auxiliar para preencher o formulário
    // ==========================================
    void preencherCamposBasicos(String tipoCurso, boolean valoresNegativos) {

        // 1️⃣ Clicar no botão "Cadastrar curso"
        WebElement botaoCadastrar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[span/span[text()='Cadastrar curso']]")));
        new Actions(driver).moveToElement(botaoCadastrar).click().perform();

        // 2️⃣ Preencher campos fixos
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[aria-label='Nome do curso']")))
                .sendKeys("Curso de Automação com Selenium - " + tipoCurso);

        // 3️⃣ Campo de descrição
        WebElement descricao = driver.findElement(By.cssSelector("textarea[aria-label='Descrição do curso']"));
        descricao.sendKeys("Curso criado automaticamente para testes de QA Beedoo.");

        // 4️⃣ Campos fixos
        driver.findElement(By.cssSelector("input[aria-label='Instrutor']"))
                .sendKeys("Julio Papito");

        driver.findElement(By.cssSelector("input[aria-label='Url da imagem de capa']"))
                .sendKeys("https://meusite.com/imagem.png");

        driver.findElement(By.cssSelector("input[aria-label='Data de início']"))
                .sendKeys("10-11-2025");

        driver.findElement(By.cssSelector("input[aria-label='Data de fim']"))
                .sendKeys("15-12-2025");

        // 5️⃣ Campo de vagas
        String vagas = valoresNegativos ? "-1000000000" : "205";
        driver.findElement(By.cssSelector("input[aria-label='Número de vagas']"))
                .sendKeys(vagas);

        // 6️⃣ Selecionar tipo de curso
        WebElement dropdownTipo = driver.findElement(By.cssSelector("input[aria-label='Tipo de curso']"));
        dropdownTipo.click();

        WebElement opcaoTipo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option']//span[text()='" + tipoCurso + "']")));
        opcaoTipo.click();

        // 7️⃣ Campo condicional
        if (tipoCurso.equalsIgnoreCase("Presencial")) {
            WebElement endereco = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[aria-label='Endereço']")));
            endereco.sendKeys("Av. Paulista, 1000 - São Paulo/SP");
        } else if (tipoCurso.equalsIgnoreCase("Online")) {
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[aria-label='Link de inscrição']")));
            link.sendKeys("https://meusite.com/inscricao");
        }

        // 8️⃣ Cadastrar curso
        WebElement botaoSalvar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Cadastrar curso']]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botaoSalvar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoSalvar);
    }

    // ==========================================
    // 🔎 Método auxiliar para verificar mensagens
    // ==========================================
    boolean validarMensagem(String textoEsperado) {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'q-notification__message') and contains(text(),'" + textoEsperado + "')]")));
            return msg.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ==========================================
    // 🔹 Casos de Teste
    // ==========================================
    @Test
    @DisplayName("CT001 - Cadastro de Curso Presencial")
    void cadastrarCursoPresencial() {
        preencherCamposBasicos("Presencial", false);
        boolean sucesso = validarMensagem("Curso cadastrado com sucesso!");
        Assertions.assertTrue(sucesso, "Mensagem de sucesso não apareceu após cadastrar curso presencial!");
    }

    @Test
    @DisplayName("CT002 - Cadastro de Curso Online")
    void cadastrarCursoOnline() {
        preencherCamposBasicos("Online", false);
        boolean sucesso = validarMensagem("Curso cadastrado com sucesso!");
        Assertions.assertTrue(sucesso, "Mensagem de sucesso não apareceu após cadastrar curso online!");
    }

    @Test
    @DisplayName("CT003 - Cadastro de Curso com Valores Negativos")
    void cadastrarCursoComValoresNegativos() {
        preencherCamposBasicos("Presencial", true);
        boolean sucesso = validarMensagem("Curso cadastrado com sucesso!");
        Assertions.assertFalse(sucesso, "O sistema aceitou valores negativos!");
    }

    // ==========================================
    // 🧹 CT004 - Excluir curso após cadastro
    // ==========================================
    @Test
    @DisplayName("CT004 - Excluir curso após cadastro")
    void excluirCursoCadastrado() {
        preencherCamposBasicos("Online", false);
        boolean sucessoCadastro = validarMensagem("Curso cadastrado com sucesso!");
        Assertions.assertTrue(sucessoCadastro, "Falha ao cadastrar curso para exclusão.");

        // 1️⃣ Clicar no botão Excluir curso
        WebElement botaoExcluir = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Excluir curso']]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botaoExcluir);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botaoExcluir);

        // 2️⃣ Verificar mensagem
        boolean msgExclusao = validarMensagem("Curso excluído com sucesso!");
        Assertions.assertTrue(msgExclusao, "Mensagem de exclusão não foi exibida corretamente.");
    }
}
