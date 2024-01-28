import tkinter as tk
from tkinter import messagebox

class SudokuSolverGUI:
    def __init__(self, master):
        self.master = master
        self.master.title("Sudoku Solver")

        self.text_entries = [[None]*9 for _ in range(9)]

        # Create Sudoku grid
        for i in range(9):
            for j in range(9):
                entry = tk.Entry(self.master, width=2, font=('Arial', 16), justify='center')
                entry.grid(row=i, column=j, padx=2, pady=2)
                self.text_entries[i][j] = entry
                entry.bind('<Up>', lambda event, row=i, col=j: self.move_focus(row-1, col))
                entry.bind('<Down>', lambda event, row=i, col=j: self.move_focus(row+1, col))
                entry.bind('<Left>', lambda event, row=i, col=j: self.move_focus(row, col-1))
                entry.bind('<Right>', lambda event, row=i, col=j: self.move_focus(row, col+1))
                if (i // 3 + j // 3) % 2 == 0:
                    entry.configure(bg='#f0f0f0')  # Light gray background
                else:
                    entry.configure(bg='#ffffff')  # White background

        # Solve button
        solve_button = tk.Button(self.master, text="Solve", command=self.solve_sudoku, font=('Arial', 14), bg='#4caf50', fg='white', padx=10, pady=5)
        solve_button.grid(row=9, columnspan=9, pady=10)

    def move_focus(self, row, col):
        if 0 <= row < 9 and 0 <= col < 9:
            self.text_entries[row][col].focus()

    def solve_sudoku(self):
        sudoku_grid = [[int(self.text_entries[i][j].get() or 0) for j in range(9)] for i in range(9)]
        if self.solve_sudoku_recursive(sudoku_grid):
            self.display_solution(sudoku_grid)
        else:
            messagebox.showinfo("Sudoku Solver", "No solution exists.")

    def solve_sudoku_recursive(self, grid):
        empty_location = self.find_empty_location(grid)

        if not empty_location:
            return True

        row, col = empty_location

        for num in range(1, 10):
            if self.is_safe(grid, row, col, num):
                grid[row][col] = num

                if self.solve_sudoku_recursive(grid):
                    return True

                grid[row][col] = 0

        return False

    def find_empty_location(self, grid):
        for i in range(9):
            for j in range(9):
                if grid[i][j] == 0:
                    return i, j
        return None

    def is_safe(self, grid, row, col, num):
        return (
            all(num != grid[row][i] for i in range(9)) and
            all(num != grid[i][col] for i in range(9)) and
            all(num != grid[row // 3 * 3 + i // 3][col // 3 * 3 + i % 3] for i in range(9))
        )

    def display_solution(self, grid):
        for i in range(9):
            for j in range(9):
                self.text_entries[i][j].delete(0, tk.END)
                self.text_entries[i][j].insert(0, grid[i][j])
                self.text_entries[i][j].config(fg='#2196F3', font=('Arial', 16, 'bold'))

def main():
    root = tk.Tk()
    app = SudokuSolverGUI(root)
    root.mainloop()

if __name__ == "__main__":
    main()
