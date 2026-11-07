
const API_URL = "/api/birthdays";

// Load today's date
function displayCurrentDate() {
    const today = new Date();

    document.getElementById("current-date").textContent =
        today.toLocaleDateString("en-LK", {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric"
        });
}

// Fetch today's birthdays
async function loadTodayBirthdays() {
    const container = document.getElementById("today-birthdays");

    try {
        const response = await fetch(`${API_URL}/today`);

        if (!response.ok) {
            throw new Error("Failed to load today's birthdays");
        }

        const birthdays = await response.json();

        container.innerHTML = "";

        if (birthdays.length === 0) {
            container.innerHTML =
                '<p class="empty-message">No birthdays today. Enjoy the day! 😊</p>';
            return;
        }

        birthdays.forEach(birthday => {
            const card = document.createElement("div");
            card.className = "birthday-card";

            const name = document.createElement("strong");
            name.textContent = `🎂 ${birthday.name}`;

            const message = document.createElement("span");
            message.textContent = "Happy Birthday! 🎉";

            card.append(name, message);
            container.appendChild(card);
        });

    } catch (error) {
        console.error(error);
        container.textContent = "Unable to load today's birthdays.";
    }
}

// Fetch all birthdays
async function loadAllBirthdays() {
    const container = document.getElementById("all-birthdays");

    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load birthdays");
        }

        const birthdays = await response.json();

        container.innerHTML = "";

        if (birthdays.length === 0) {
            container.innerHTML =
                '<p class="empty-message">No birthdays have been added yet.</p>';
            return;
        }


        birthdays.forEach(birthday => {
            const card = document.createElement("div");
            card.className = "birthday-card";

            const name = document.createElement("strong");
            name.textContent = birthday.name;

            const date = document.createElement("span");
            date.textContent = birthday.dateOfBirth;

            const editButton = document.createElement("button");
            editButton.textContent = "Edit";
            editButton.className = "edit-button";

            editButton.addEventListener("click", () => {
                editBirthday(birthday);
            });

            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.className = "delete-button";

            deleteButton.addEventListener("click", () => {
                deleteBirthday(birthday.id);
            });

            card.append(name, date, editButton, deleteButton);
            container.appendChild(card);
        });


    } catch (error) {
        console.error(error);
        container.textContent = "Unable to load birthdays.";
    }
}

// Add a new birthday
async function addBirthday(event) {

    event.preventDefault();

    const name = document.getElementById("name").value;
    const dateOfBirth =
        document.getElementById("dateOfBirth").value;

    const messageElement =
        document.getElementById("form-message");

    try {

        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                dateOfBirth: dateOfBirth
            })
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(
                result.message || "Failed to add birthday"
            );
        }

        messageElement.textContent =
            "Birthday added successfully! 🎉";

        messageElement.style.color = "green";

        document.getElementById("birthday-form").reset();

        // Refresh the dashboard
        await loadTodayBirthdays();
        await loadAllBirthdays();

    } catch (error) {

        messageElement.textContent = error.message;
        messageElement.style.color = "red";

    }
}


async function deleteBirthday(id) {

    const confirmed = confirm(
        "Are you sure you want to delete this birthday?"
    );

    if (!confirmed) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Failed to delete birthday");
        }

        alert("Birthday deleted successfully!");

        await loadTodayBirthdays();
        await loadAllBirthdays();

    } catch (error) {
        console.error(error);
        alert(error.message);
    }
}



let selectedBirthdayId = null;

// Open the edit dialog
function editBirthday(birthday) {

    selectedBirthdayId = birthday.id;

    document.getElementById("edit-name").value =
        birthday.name;

    document.getElementById("edit-date").value =
        birthday.dateOfBirth;

    document.getElementById("edit-message").textContent = "";

    document.getElementById("edit-dialog").showModal();
}

// Save edited birthday
async function saveBirthdayChanges(event) {

    event.preventDefault();

    const name = document.getElementById("edit-name").value;
    const dateOfBirth = document.getElementById("edit-date").value;

    const messageElement =
        document.getElementById("edit-message");

    try {

        const response = await fetch(
            `${API_URL}/${selectedBirthdayId}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name,
                    dateOfBirth: dateOfBirth
                })
            }
        );

        const result = await response.json();

        if (!response.ok) {
            throw new Error(
                result.message || "Failed to update birthday"
            );
        }

        document.getElementById("edit-dialog").close();

        await loadTodayBirthdays();
        await loadAllBirthdays();

    } catch (error) {

        messageElement.textContent = error.message;
        messageElement.style.color = "red";

    }
}

// Connect form submission
document
    .getElementById("edit-birthday-form")
    .addEventListener("submit", saveBirthdayChanges);

// Connect cancel button
document
    .getElementById("cancel-edit")
    .addEventListener("click", () => {
        document.getElementById("edit-dialog").close();
    });




// Initialize dashboard
displayCurrentDate();
loadTodayBirthdays();
loadAllBirthdays();

// Connect form submission to the function
document
    .getElementById("birthday-form")
    .addEventListener("submit", addBirthday);
