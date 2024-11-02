'use strict';

const storageKey = 'theme';

// Função para obter o tema armazenado
const getStoredTheme = () => localStorage.getItem(storageKey);

// Função para armazenar o tema
const setStoredTheme = theme => localStorage.setItem(storageKey, theme);

// Função para obter o tema preferido
const getPreferredTheme = () => {
    const storedTheme = getStoredTheme();
    if (storedTheme) {
        return storedTheme;
    }
    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
};

// Função para mudar a cor do SVG com base no tema
const changeSVG = (theme) => {
    let svg = document.querySelectorAll('svg');
    if (theme === 'dark') {
        for (const s of svg) {
            s.style.fill = 'rgb(255,255,255)';
        }
    } else {
        for (const s of svg) {
            s.style.fill = 'rgb(0,0,0)';
        }
    }
};

// Função para definir o tema
const setTheme = (theme) => {
    document.documentElement.setAttribute('data-theme', theme);
    changeSVG(theme);
};

// Configuração inicial do tema
setTheme(getPreferredTheme());

// Função para mostrar o tema ativo
const showActiveTheme = (theme) => {
    const themeToggleButton = document.querySelector('#theme-toggle');
    const activeThemeIcon = document.querySelector('.theme-icon-active use');
    const btnToActive = themeToggleButton;
    const svgOfActiveBtn = btnToActive.querySelector('svg use').getAttribute('href');

    btnToActive.classList.add('active');
    btnToActive.setAttribute('aria-pressed', 'true');
    activeThemeIcon.setAttribute('href', svgOfActiveBtn);
};

// Adiciona um listener para mudanças no esquema de cores do sistema
window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
    const storedTheme = getStoredTheme();
    if (storedTheme !== 'light' && storedTheme !== 'dark') {
        setTheme(getPreferredTheme());
    }
});

// Configuração inicial do DOMContentLoaded
window.addEventListener('DOMContentLoaded', () => {
    showActiveTheme(getPreferredTheme());

    // Listener para o botão de troca de tema
    const button = document.querySelector('#theme-toggle');
    if (button) {
        button.addEventListener('click', () => {
            switchTheme();
        });
    }
});

// Função para trocar o tema
const switchTheme = () => {
    let theme = getStoredTheme();
    let themeValue = (theme === 'light') ? 'dark' : 'light';
    setStoredTheme(themeValue);
    setTheme(themeValue);
    changeSVG(themeValue);
};
