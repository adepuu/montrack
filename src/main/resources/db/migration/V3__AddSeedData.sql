-- Seed data for roles table
INSERT INTO
  roles (name, created_at, updated_at)
VALUES
  ('ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (
    'MODERATOR',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );

-- Seed data for users table
INSERT INTO
  users (
    email,
    password,
    pin,
    profile_picture_url,
    is_onboarding_finished,
    created_at,
    updated_at
  )
VALUES
  (
    'alice@example.com',
    'hashedpassword1',
    '1234',
    'http://example.com/profiles/alice.jpg',
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    'bob@example.com',
    'hashedpassword2',
    '5678',
    'http://example.com/profiles/bob.jpg',
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    'charlie@example.com',
    'hashedpassword3',
    '9012',
    'http://example.com/profiles/charlie.jpg',
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );

-- Seed data for user_roles table
INSERT INTO
  user_roles (user_id, role_id, assigned_at)
VALUES
  (
    (
      SELECT
        user_id
      FROM
        users
      WHERE
        email = 'alice@example.com'
    ),
    (
      SELECT
        role_id
      FROM
        roles
      WHERE
        name = 'ADMIN'
    ),
    CURRENT_TIMESTAMP
  ),
  (
    (
      SELECT
        user_id
      FROM
        users
      WHERE
        email = 'bob@example.com'
    ),
    (
      SELECT
        role_id
      FROM
        roles
      WHERE
        name = 'USER'
    ),
    CURRENT_TIMESTAMP
  ),
  (
    (
      SELECT
        user_id
      FROM
        users
      WHERE
        email = 'charlie@example.com'
    ),
    (
      SELECT
        role_id
      FROM
        roles
      WHERE
        name = 'MODERATOR'
    ),
    CURRENT_TIMESTAMP
  );

-- Seed data for wallet table
INSERT INTO
  wallet (
    is_active,
    name,
    current_expense,
    current_income,
    allocated_budget,
    created_at,
    updated_at
  )
VALUES
  (
    true,
    'Personal Wallet',
    5000000,
    15000000,
    20000000,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    false,
    'Business Wallet',
    25000000,
    50000000,
    80000000,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );

-- Seed data for pocket table
INSERT INTO
  pocket (
    name,
    emoji_code,
    allocated_budget,
    description,
    used_amount,
    wallet_id,
    created_at,
    updated_at
  )
VALUES
  (
    'Groceries',
    '🥦',
    2000000,
    'Monthly grocery budget',
    500000,
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Personal Wallet'
    ),
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    'Entertainment',
    '🎬',
    1000000,
    'Movies and outings',
    200000,
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Personal Wallet'
    ),
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    'Office Supplies',
    '📎',
    5000000,
    'Stationery and equipment',
    1000000,
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Business Wallet'
    ),
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );

-- Seed data for goal table
INSERT INTO
  goal (
    name,
    target_amount,
    current_amount,
    wallet_id,
    attachment_url,
    created_at,
    updated_at
  )
VALUES
  (
    'Vacation Trip',
    10000000,
    3000000,
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Personal Wallet'
    ),
    'http://example.com/goals/vacation.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    'New Laptop',
    15000000,
    5000000,
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Business Wallet'
    ),
    'http://example.com/goals/laptop.jpg',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );

-- Seed data for trx table
INSERT INTO
  trx (
    value,
    description,
    attachment_url,
    pocket_id,
    wallet_id,
    goal_id,
    created_at,
    updated_at
  )
VALUES
  (
    1000000,
    'Bought groceries',
    'http://example.com/trx/receipt1.jpg',
    (
      SELECT
        pocket_id
      FROM
        pocket
      WHERE
        name = 'Groceries'
    ),
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Personal Wallet'
    ),
    NULL,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    1500000,
    'Movie tickets',
    'http://example.com/trx/receipt2.jpg',
    (
      SELECT
        pocket_id
      FROM
        pocket
      WHERE
        name = 'Entertainment'
    ),
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Personal Wallet'
    ),
    NULL,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    2000000,
    'Purchased office chairs',
    'http://example.com/trx/receipt3.jpg',
    (
      SELECT
        pocket_id
      FROM
        pocket
      WHERE
        name = 'Office Supplies'
    ),
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Business Wallet'
    ),
    NULL,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  ),
  (
    5000000,
    'Saved towards Vacation Trip',
    NULL,
    NULL,
    (
      SELECT
        wallet_id
      FROM
        wallet
      WHERE
        name = 'Personal Wallet'
    ),
    (
      SELECT
        goal_id
      FROM
        goal
      WHERE
        name = 'Vacation Trip'
    ),
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );